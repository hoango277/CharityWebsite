package com.javaweb.controller;

import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.VolunteerResponse;
import com.javaweb.model.response.*;
import com.javaweb.service.CharityProgramService;
import com.javaweb.service.UserService;
import com.javaweb.service.VolunteerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private UserService userService;

    @GetMapping("")
    public String getAllCharityProgram(Model model,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "6") int size) throws ParseException {
        Page<CharityProgramResponse> list = charityProgramService.getAllCharityPrograms(page, size);
        for (CharityProgramResponse project : list) {
            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0) {
                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
                fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
                project.setFundingPercentage(fundingPercentage);
            }
        }
        model.addAttribute("projects", list.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalItems", list.getTotalElements());
        return "charityPrograms/charity-program";
    }

    @GetMapping("/{id}")
    public String getCharityProgramById(@PathVariable("id") Long id,
                                        Model model,
                                        @RequestParam(value = "userId") Long userId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size
                                       ) throws ParseException {
        CharityProgramResponse charityProgramResponse = charityProgramService.getCharityProgramById(id);
        Page<VolunteerResponse> volunteerResponse = volunteerService.getAllVolunteers(id, page, size);
        InfoUserResponse userResponse = (InfoUserResponse) userService.getUserById(String.valueOf(userId)).getData();

        double fundingPercentage = (double) charityProgramResponse.getTotalAmount() / charityProgramResponse.getAmountNeeded() * 100;
        fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
        charityProgramResponse.setFundingPercentage(fundingPercentage);

        model.addAttribute("projects", charityProgramResponse);
        model.addAttribute("totalPages", volunteerResponse.getTotalPages());
        model.addAttribute("volunteers", volunteerResponse.getContent());
        model.addAttribute("user", userResponse);
        model.addAttribute("page", page);
        model.addAttribute("userID", String.valueOf(userId));

        return "charityPrograms/detail-charity-program";
    }


    @GetMapping("/search/result")
    public String getAllCharityProgramByKeyword(Model model, @RequestParam("keyword") String keyword,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "6") int size) throws ParseException {
        Page<CharityProgramResponse> list = charityProgramService.getCharityProgramByKeyword(page, size,keyword);
        for (CharityProgramResponse project : list) {
            System.out.println(project.getImage());
            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0) {
                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
                fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
                project.setFundingPercentage(fundingPercentage);
            }
        }

        model.addAttribute("projects", list.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalItems", list.getTotalElements());
        return "charityPrograms/charity-program";
    }



}
