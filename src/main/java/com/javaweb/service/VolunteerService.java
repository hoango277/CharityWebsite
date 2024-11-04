package com.javaweb.service;

import com.javaweb.model.response.ResponseDTO;

public interface VolunteerService {
    ResponseDTO getAllVolunteers(long charityProgramID);

}
