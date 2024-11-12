package com.javaweb.service;

import com.javaweb.model.dto.VolunteerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;

public interface VolunteerService {
    ResponseDTO getAllVolunteers(long charityProgramID);

    StatusResponse addVolunteer(long charityProgramID, long moneyVolunteer, long userID);


}
