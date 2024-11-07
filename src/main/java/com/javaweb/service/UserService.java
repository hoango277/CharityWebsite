package com.javaweb.service;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    ResponseDTO getAllUser();
    ResponseDTO getUserById(Long userId);

    StatusResponse deleteUser(Long userId);
}
