package com.javaweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String allUser(Model model) {
        ResponseDTO responseDTO = userService.getAllUser();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userAccountsJson = objectMapper.writeValueAsString(responseDTO.getData());
            model.addAttribute("userAccounts", userAccountsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            model.addAttribute("userAccounts", "[]"); // Truyền dữ liệu rỗng nếu có lỗi
        }

        return "manager/user-account";
    }
}
