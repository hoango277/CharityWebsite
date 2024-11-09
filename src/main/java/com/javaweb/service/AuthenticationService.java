package com.javaweb.service;


import com.javaweb.model.dto.LoginDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.ResetPasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.TokenResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface AuthenticationService {
    UserDTO getUserById(Long userId);

    TokenResponse authenticate(LoginDTO request);

    TokenResponse refresh(HttpServletRequest request);

    String logout(HttpServletRequest request);

    StatusResponse forgotPassword(String email) throws MessagingException, UnsupportedEncodingException;

    StatusResponse resetPassword(String secretKey);

    StatusResponse changePassword(ResetPasswordDTO resetPasswordDTO);

    StatusResponse registerUser(RegisterDTO registerDTO);

    ResponseDTO updateUser(Long userId, UserDTO userDTO);
}
