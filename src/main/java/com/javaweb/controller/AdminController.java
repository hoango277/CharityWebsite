package com.javaweb.controller;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.InfoUserResponse;
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
    @GetMapping("/account")
    public String accountPage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {
        List<InfoUserResponse> listUser = userService.getAllUser(pageNumber,pageSize);
        model.addAttribute("listUser", listUser);
        return "admin/account";
    }
}
