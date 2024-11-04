package com.javaweb.service.impl;

import com.javaweb.converter.UserConverter;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public ResponseDTO getAllUser() {
        List<UserEntity> listUser = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (UserEntity userEntity : listUser) {
            UserDTO user = userConverter.convertToDTO(userEntity);
            listUserDTO.add(user);
        }
        return ResponseDTO.builder()
                .data(listUserDTO)
                .message("Find successfully")
                .detail("Detail message")
                .build();
    }
}
