package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.converter.UserConverter;
import com.javaweb.converter.VolunteerConverter;
import com.javaweb.entity.*;
import com.javaweb.exception.InvalidDataException;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.*;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private VolunteerConverter volunteerConverter;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<UserResponse> getAllUser(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<UserEntity> pageUser = userRepository.findAll(pageable);

        List<UserResponse> userResponses = pageUser.getContent().stream()
                .map(userConverter::convertToUserResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(userResponses, pageable, pageUser.getTotalElements());
    }

    @Override
    public ResponseDTO getUserById(String userId) throws ParseException {
        UserEntity userEntity = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        if (userEntity.getStatus().equals(0)){
            throw new InvalidDataException("User is not active!");
        }
        InfoUserResponse userResponse = userConverter.convertToInfoUserResponse(userEntity);
        WalletEntity walletEntity = userEntity.getWallet();
        Set<VolunteerEntity> volunteerEntities = userEntity.getVolunteerPrograms();
        List<VolunteerResponse> volunteerResponses = new ArrayList<>();
        for (VolunteerEntity volunteerEntity : volunteerEntities) {
            volunteerResponses.add(volunteerConverter.convertToResponse(volunteerEntity));
        }
        Set<TransactionEntity> transaction = userEntity.getTransactions();
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity transactionEntity : transaction) {
            transactionResponses.add(transactionConverter.convertToResponse(transactionEntity));
        }
        userResponse.setWallet(modelMapper.map(walletEntity, WalletResponse.class));
        userResponse.setVolunteers(volunteerResponses);
        userResponse.setTransactions(transactionResponses);

        return ResponseDTO.builder()
                .data(userResponse)
                .message("Find successfully")
                .detail("Detail message")
                .build();
    }

    @Override
    public StatusResponse deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        if (userEntity.getStatus().equals(0)){
            return StatusResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Delete failed: User is not active!")
                    .build();
        }
        userEntity.setStatus(0);
        userRepository.save(userEntity);
        return StatusResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Delete successfully!")
                .build();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

}
