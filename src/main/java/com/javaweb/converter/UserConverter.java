package com.javaweb.converter;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO (UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public InfoUserResponse convertToInfoUserResponse (UserEntity userEntity){
        return modelMapper.map(userEntity, InfoUserResponse.class);
    }
}
