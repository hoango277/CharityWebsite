package com.javaweb.controller.restcontroller;



import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("donationList")
@Slf4j
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/{charityProgramID}")
    public StatusResponse addVolunteer(@PathVariable("charityProgramID") long charityProgramID, long userID, long money)
    {
        return volunteerService.addVolunteer(charityProgramID, money, userID);
    }



}
