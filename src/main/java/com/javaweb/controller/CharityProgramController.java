package com.javaweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects")
public class CharityProgramController {
    @GetMapping()
    public String getCharityProgram() {
        return "charityPrograms/charity-program";
    }

    @GetMapping("/1")
    public String getDetailCharity(Model model) {
        return "charityPrograms/detail-charity-program";
    }
}
