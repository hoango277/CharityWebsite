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

@RestController
@RequestMapping("/test")
public class CharityProgramController {

    @Autowired
    private CharityProgramService charityProgramService;

//    @GetMapping("")
//    public String getAllCharityProgram(Model model) throws ParseException {
//        List<CharityProgramResponse> list = charityProgramService.getAllCharityPrograms();
//        System.out.println("Total projects: " + list.size());
//        for (CharityProgramResponse project : list) {
//            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0) {
//                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
//                project.setFundingPercentage(fundingPercentage);
//            }
//        }
//        model.addAttribute("projects", list);
//        return "charityPrograms/charity-program";
//    }

    @GetMapping("")
    public ResponseEntity<List<CharityProgramResponse>> getAllCharityProgram() throws ParseException {
        List<CharityProgramResponse> list = charityProgramService.getAllCharityPrograms();
        System.out.println("Total projects: " + list.size());

        for (CharityProgramResponse project : list) {
            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0 && project.getTotalAmount() != null) {
                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
                project.setFundingPercentage(fundingPercentage);
            }
        }

        return ResponseEntity.ok(list);
    }
}
