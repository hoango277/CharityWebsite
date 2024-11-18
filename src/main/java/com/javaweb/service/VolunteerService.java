package com.javaweb.service;
import com.javaweb.model.response.VolunteerResponse;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface VolunteerService {
    Page<VolunteerResponse> getAllVolunteers(long charityProgramID, int page, int size) throws ParseException;

    void addVolunteer(long charityProgramID, long moneyVolunteer, long userID, boolean anonymous);



}
