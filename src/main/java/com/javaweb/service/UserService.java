package com.javaweb.service;

import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    ResponseDTO getAllUser();
    ResponseDTO getUserById(String userId);

    StatusResponse deleteUser(Long userId);
}
