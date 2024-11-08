package com.javaweb.converter;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.CharityProgramResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharityProgramConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CharityProgramResponse convertToResponse(CharityProgramEntity charityProgramEntity) {
        return modelMapper.map(charityProgramEntity, CharityProgramResponse.class);
    }

    public CharityProgramEntity convertToEntity(CharityProgramResponse charityProgramResponse) {
        return modelMapper.map(charityProgramResponse, CharityProgramEntity.class);
    }
}
