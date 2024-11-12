package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;

public interface UserService {
    UserDetailsService userDetailsService();
    ResponseDTO getAllUser();
    ResponseDTO getUserById(String userId) throws ParseException;

    StatusResponse deleteUser(Long userId);
}
