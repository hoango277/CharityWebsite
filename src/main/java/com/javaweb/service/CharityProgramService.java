package com.javaweb.service;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;

public interface CharityProgramService {

    ResponseDTO getAllCharityPrograms();

    ResponseDTO getCharityProgramById(Long id);

    ResponseDTO createCharityProgram(CharityProgramEntity charityProgramEntity);

    ResponseDTO updateCharityProgram(Long id, CharityProgramEntity charityProgramEntity);

    StatusResponse deleteCharityProgram(Long id);
}
