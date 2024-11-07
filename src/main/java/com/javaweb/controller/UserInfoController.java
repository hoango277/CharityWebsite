package com.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInfoController {
    @RequestMapping("/account-info")
    public String infoUser(){
        return "users/info-user";
    }
}
