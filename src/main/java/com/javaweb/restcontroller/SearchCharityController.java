package com.javaweb.restcontroller;

import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.service.CharityProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Validated
@Slf4j
public class SearchCharityController {
    @Autowired
    private CharityProgramService charityProgramService;

    @GetMapping("/search/suggest")
    public ResponseEntity<List<CharityProgramResponse>> getAllCharityProgramByKeyword(@RequestParam("keyword") String keyword,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "4") int size) {
        Page<CharityProgramResponse> list = charityProgramService.getCharityProgramByKeyword(page, size,keyword);
        for (CharityProgramResponse project : list) {
            if (project.getAmountNeeded() != null && project.getAmountNeeded() > 0) {
                double fundingPercentage = (double) project.getTotalAmount() / project.getAmountNeeded() * 100;
                fundingPercentage = Math.round(fundingPercentage * 100.0) / 100.0;
                project.setFundingPercentage(fundingPercentage);
            }
        }
        return ResponseEntity.ok(list.getContent());
    }
}
