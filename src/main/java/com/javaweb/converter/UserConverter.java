package com.javaweb.converter;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
import com.javaweb.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


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
    public UserResponse convertToUserResponse (UserEntity userEntity){
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setWallet(userEntity.getWallet().getTotalAmount().toString());
        Set<RoleEntity> roleEntities = userEntity.getRoles();
        String roles = roleEntities.stream()
                .map(RoleEntity::getRoleName)
                .collect(Collectors.joining(","));
        userResponse.setRole(roles);
        return userResponse;
    }
}
