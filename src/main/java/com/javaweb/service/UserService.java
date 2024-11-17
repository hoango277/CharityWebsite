package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;

public interface UserService {
    UserDetailsService userDetailsService();
    Page<UserResponse> getAllUser(Integer pageNumber, Integer pageSize);
    ResponseDTO getUserById(String userId) throws ParseException;

    StatusResponse deleteUser(Long userId);
}
