package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;

public interface VolunteerService {
    ResponseDTO getAllVolunteers(long charityProgramID);
}
