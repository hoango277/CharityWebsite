package com.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgetPasswordController {
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "users/forgot-password";
    }
}
