package com.javaweb.service.impl;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.TokenEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.entity.WalletEntity;
import com.javaweb.exception.InvalidDataException;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.model.dto.LoginDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.ResetPasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.TokenResponse;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.WalletRepository;
import com.javaweb.service.TokenService;
import com.javaweb.service.AuthenticationService;
import com.javaweb.utils.JWTTokenUtils;
import com.javaweb.utils.MessageUtils;
import com.javaweb.utils.TokenType;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTTokenUtils jwtTokenUtils;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private MailService mailService;

    @Override
    public UserDTO getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + userId));

        if (userEntity.getStatus().equals(0)){
            throw new InvalidDataException("User is not active!");
        }
        return UserDTO.builder()
                .userName(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    @Override
    public TokenResponse authenticate(LoginDTO request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserEntity userEntity = userRepository.findByUserName(request.getUsername()).orElseThrow(()-> new ResourceNotFoundException("Username or Password is incorrect"));

        String accessToken = jwtTokenUtils.generateToken(userEntity);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userEntity);
        // save to database
        tokenService.saveToken(TokenEntity.builder()
                        .name(userEntity.getUsername())
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                .build());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userEntity.getUserId())
                .build();
    }

    @Override
    public TokenResponse refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("referer");
        if (refreshToken == null) {
            throw new InvalidDataException("Token must be not blank!");
        }

        final String name = jwtTokenUtils.extractUser(refreshToken, TokenType.REFRESH_TOKEN);

        Optional<UserEntity> userEntity = userRepository.findByUserName(name);
        if (!jwtTokenUtils.validateToken(refreshToken,TokenType.REFRESH_TOKEN, userEntity.get())){
            throw new InvalidDataException("Token is invalid");
        }

        String accessToken = jwtTokenUtils.generateToken(userEntity.get());

        // save to database
        tokenService.saveToken(TokenEntity.builder()
                        .name(userEntity.get().getUsername())
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                .build());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userEntity.get().getUserId())
                .build();
    }

    @Override
    public String logout(HttpServletRequest request) {
        String accessToken = request.getHeader("check-token");
        if (accessToken == null) {
            throw new InvalidDataException("Token must be not blank!");
        }
        final String name = jwtTokenUtils.extractUser(accessToken, TokenType.ACCESS_TOKEN);

//        RedisToken redisToken = redisTokenService.getById(name);
//        redisTokenService.delete(redisToken.getId());
        TokenEntity token = tokenService.getByName(name);
        tokenService.deleteToken(token.getId());

        return "Logout successfully!";
    }

    @Override
    public StatusResponse forgotPassword(String email) throws MessagingException, UnsupportedEncodingException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!userEntity.isEnabled() || userEntity.getStatus().equals(0)){
            throw new InvalidDataException("User is disabled");
        }

        String resetToken = jwtTokenUtils.generateResetToken(userEntity);

        //kafkaTemplate.send("confirm-forgot-password-topic", String.format("email=%s,id=%s,code=%s", email, userEntity.getId(),resetToken));
        mailService.sendConfirmLink(email,userEntity.getUserId(),resetToken);
        log.info("Reset token: " + resetToken);
        return StatusResponse.builder()
                .message("Check your email")
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public StatusResponse resetPassword(String secretKey) {
        UserEntity userEntity = isValidUserByToken(secretKey);
        userEntity.setPassword(passwordEncoder.encode(MessageUtils.DEFAULT_PASSWORD));
        userRepository.save(userEntity);
        return StatusResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Reset successfully")
                .build();
    }

    @Override
    public StatusResponse changePassword(ResetPasswordDTO resetPasswordDTO) {
        UserEntity userEntity = isValidUserByToken(resetPasswordDTO.getSecretKey());
        if (!resetPasswordDTO.getPassword().equals(resetPasswordDTO.getConfirmPassword())) {
            throw new InvalidDataException("Passwords do not match");
        }
        userEntity.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userRepository.save(userEntity);

        return StatusResponse.builder()
                .message("Change password success!")
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public StatusResponse registerUser(RegisterDTO registerDTO) {
        Optional<UserEntity> existEntity = userRepository.findByUserNameOrEmail(registerDTO.getUserName(), registerDTO.getEmail());

        if (existEntity.isPresent()) {
            throw new InvalidDataException("User already exists!");
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new InvalidDataException("Passwords do not match");
        }
        UserEntity userEntity = modelMapper.map(registerDTO, UserEntity.class);
        //Encode password
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        //Active account
        userEntity.setStatus(1);

        //Set role user
        RoleEntity roleEntity = roleRepository.findById(MessageUtils.USER_ID)
                .orElseThrow(()->new ResourceNotFoundException("Role does not exist!"));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntity);
        userEntity.setRoles(roles);

        // Create wallet
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setTotalAmount(0L);
        walletEntity.setUser(userEntity);

        userEntity.setWallet(walletEntity);

        userRepository.save(userEntity);

        return StatusResponse.builder()
                .message("Register success!")
                .status(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public ResponseDTO updateUser(Long userId, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        UserEntity saveUserEntity = modelMapper.map(userDTO, UserEntity.class);
        UserDTO userResponse = modelMapper.map(saveUserEntity, UserDTO.class);
        saveUserEntity.setUserId(userId);
        saveUserEntity.setRoles(userEntity.getRoles());
        saveUserEntity.setWallet(userEntity.getWallet());
        saveUserEntity.setStatus(userEntity.getStatus());
        if (userDTO.getPassword() != null){
            saveUserEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(saveUserEntity);
        return ResponseDTO.builder()
                .data(userResponse)
                .message("Update info user successfully")
                .detail("Detail message")
                .build();
    }

    private UserEntity isValidUserByToken(String token) {
        final String name = jwtTokenUtils.extractUser(token,TokenType.RESET_TOKEN);
        UserEntity userEntity = userRepository.findByUserName(name).orElseThrow(()-> new ResourceNotFoundException("Username or Password is incorrect"));
        if (!userEntity.isEnabled() || userEntity.getStatus().equals("0")){
            throw new InvalidDataException("User is disabled");
        }
        if (!jwtTokenUtils.validateToken(token,TokenType.RESET_TOKEN, userEntity)){
            throw new InvalidDataException("Token is invalid");
        }
        return userEntity;
    }
}
