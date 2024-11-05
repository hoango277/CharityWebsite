package com.javaweb.converter;

import com.javaweb.entity.UserEntity;
import com.javaweb.entity.VolunteerEntity;
import com.javaweb.entity.WalletEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
import com.javaweb.model.response.VolunteerResponse;
import com.javaweb.model.response.WalletResponse;
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
    public InfoUserResponse convertToInfoUserResponse (UserEntity userEntity){
        return modelMapper.map(userEntity, InfoUserResponse.class);
    }
}
