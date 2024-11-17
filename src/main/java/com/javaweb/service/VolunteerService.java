package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.VolunteerResponse;

import java.text.ParseException;
import java.util.List;

public interface VolunteerService {
    List<VolunteerResponse> getAllVolunteers(long charityProgramID) throws ParseException;

    StatusResponse addVolunteer(long charityProgramID, long moneyVolunteer, long userID);


}
