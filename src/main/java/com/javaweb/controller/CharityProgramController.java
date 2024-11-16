package com.javaweb.controller;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.CharityProgramService;
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
    public String getCharityProgramById(@PathVariable("id") Long id, Model model) throws ParseException {
        CharityProgramResponse charityProgramResponse = charityProgramService.getCharityProgramById(id);
        model.addAttribute("projects", charityProgramResponse);
        return "charityPrograms/detail-charity-program";
    }

}
