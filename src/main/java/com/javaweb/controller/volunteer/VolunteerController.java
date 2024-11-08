package com.javaweb.controller.volunteer;

import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/donationList")
@Slf4j
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/{charityProgramID}")
    public ResponseEntity<ResponseDTO> getAllVolunteers(@PathVariable("charityProgramID") long charityProgramID) {
        ResponseDTO responseDTO = volunteerService.getAllVolunteers(charityProgramID);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
