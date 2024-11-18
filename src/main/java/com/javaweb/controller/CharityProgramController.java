package com.javaweb.controller;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.*;
import com.javaweb.service.CharityProgramService;
import com.javaweb.service.PaymentServices;
import com.javaweb.service.UserService;
import com.javaweb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class CharityProgramController {

    @Autowired
    private CharityProgramService charityProgramService;
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private PaymentServices paymentServices;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getAllCharityProgram(Model model) throws ParseException {
        List<CharityProgramResponse> list = charityProgramService.getAllCharityPrograms();
        for (CharityProgramResponse project : list) {
            System.out.println(project.getImage());
            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0) {
                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
                fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
                project.setFundingPercentage(fundingPercentage);
            }
        }
        model.addAttribute("projects", list);
        return "charityPrograms/charity-program";
    }

    @GetMapping("/{id}")
    public String getCharityProgramById(@PathVariable("id") Long id,
                                        Model model,
                                        @RequestParam(value = "userId", required = false) Long userId
    ) throws ParseException {
        CharityProgramResponse charityProgramResponse = charityProgramService.getCharityProgramById(id);
        List<VolunteerResponse> volunteerResponse =  volunteerService.getAllVolunteers(id);
        InfoUserResponse userResponse = (InfoUserResponse) userService.getUserById(String.valueOf(userId)).getData();
        double fundingPercentage = (double) charityProgramResponse.getTotalAmount() / charityProgramResponse.getAmountNeeded() * 100;
        fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
        charityProgramResponse.setFundingPercentage(fundingPercentage);
        model.addAttribute("projects", charityProgramResponse);
        model.addAttribute("volunteers", volunteerResponse);
        model.addAttribute("user", userResponse);
        model.addAttribute("userID", String.valueOf(userId));
        return "charityPrograms/detail-charity-program";
    }



}
