package com.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserInfoController {
    @GetMapping("/account-info")
    public String infoUser(){
        return "users/info-user";
    }
}
