package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
import com.javaweb.model.response.PageUserResponse;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    PageUserResponse getAllUser(Integer pageNumber, Integer pageSize);
    ResponseDTO getUserById(String userId) throws ParseException;

    StatusResponse deleteUser(Long userId);
}
