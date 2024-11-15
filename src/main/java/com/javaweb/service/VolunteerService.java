package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;

import java.text.ParseException;

public interface VolunteerService {
    ResponseDTO getAllVolunteers(long charityProgramID) throws ParseException;

    StatusResponse addVolunteer(long charityProgramID, long moneyVolunteer, long userID);


}
