package com.javaweb.service;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.StatusResponse;

import java.text.ParseException;
import java.util.List;

public interface CharityProgramService {

    List<CharityProgramResponse> getAllCharityPrograms() throws ParseException;

    CharityProgramResponse getCharityProgramById(Long id) throws ParseException;

    CharityProgramResponse createCharityProgram(CharityProgramEntity charityProgramEntity) throws ParseException;

    CharityProgramResponse updateCharityProgram(Long id, CharityProgramEntity charityProgramEntity) throws ParseException;

    StatusResponse deleteCharityProgram(Long id);
}
