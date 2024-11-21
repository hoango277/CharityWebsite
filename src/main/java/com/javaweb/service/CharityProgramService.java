package com.javaweb.service;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.StatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface CharityProgramService {

    Page<CharityProgramResponse> getAllCharityPrograms(int page, int size) throws ParseException;

    CharityProgramResponse getCharityProgramById(Long id) throws ParseException;

    CharityProgramResponse createCharityProgram(CharityProgramEntity charityProgramEntity) throws ParseException;

    CharityProgramResponse updateCharityProgram(Long id, CharityProgramEntity charityProgramEntity) throws ParseException;

    StatusResponse deleteCharityProgram(Long id);

    Page<CharityProgramResponse> getCharityProgramByKeyword(int page, int size, String keyword);
}
