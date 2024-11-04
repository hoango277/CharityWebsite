package com.javaweb.converter;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO (UserEntity userEntity){
        UserDTO result = modelMapper.map(userEntity, UserDTO.class);
        return result;
    }
}
