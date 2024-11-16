package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
import com.javaweb.model.response.StatusResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<InfoUserResponse> getAllUser(Integer pageNumber, Integer pageSize);
    ResponseDTO getUserById(String userId) throws ParseException;

    StatusResponse deleteUser(Long userId);
}
