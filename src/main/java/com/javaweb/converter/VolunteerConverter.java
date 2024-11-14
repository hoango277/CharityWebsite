package com.javaweb.converter;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.entity.VolunteerEntity;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.VolunteerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VolunteerConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CharityProgramConverter charityProgramConverter;

    private String formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public VolunteerResponse convertToResponse(VolunteerEntity volunteerEntity) throws ParseException {
        VolunteerResponse volunteerResponse = modelMapper.map(volunteerEntity, VolunteerResponse.class);
        CharityProgramEntity charityProgram = volunteerEntity.getCharityProgram();
        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(charityProgram);
        volunteerResponse.setCharityProgram(charityProgramResponse);
        volunteerResponse.setDonateDate(formatDate(volunteerEntity.getDonateDate()));
        return volunteerResponse;
    }
}
