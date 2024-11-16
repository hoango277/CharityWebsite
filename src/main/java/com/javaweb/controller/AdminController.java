package com.javaweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.InfoUserResponse;
import com.javaweb.model.response.PageUserResponse;
import com.javaweb.model.response.UserResponse;
import com.javaweb.service.UserService;
import com.javaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/all-user-account")
    public String allUser(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {
        PageUserResponse userAccounts = userService.getAllUser(pageNumber,pageSize);
        model.addAttribute("userAccounts", userAccounts.getUsers());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", userAccounts.getTotalPage());
        return "manager/user-account";
    }
}
