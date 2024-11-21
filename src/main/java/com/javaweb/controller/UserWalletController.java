package com.javaweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wallet")
public class UserWalletController {
    @GetMapping("")
    public String Wallet() {
        return "users/wallet";
    }
}
