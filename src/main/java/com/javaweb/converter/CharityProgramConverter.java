package com.javaweb.converter;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.CharityProgramResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CharityProgramConverter {
    @Autowired
    private ModelMapper modelMapper;

    private String formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public CharityProgramResponse convertToResponse(CharityProgramEntity charityProgramEntity) throws ParseException {
        CharityProgramResponse charityProgramResponse = modelMapper.map(charityProgramEntity, CharityProgramResponse.class);
        charityProgramResponse.setStartDate(formatDate(charityProgramEntity.getStartDate()));
        charityProgramResponse.setEndDate(formatDate(charityProgramEntity.getEndDate()));
        return charityProgramResponse;
    }
}
