package com.javaweb.controller;

import com.javaweb.model.response.UserResponse;
import com.javaweb.service.UserService;
import com.javaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String adminPage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {

        Page<UserResponse> userAccounts = userService.getAllUser(pageNumber,pageSize);

        model.addAttribute("userAccounts", userAccounts.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", userAccounts.getTotalPages());
        return "admin";
    }

    @GetMapping("/user-manage")
    public String userManagePage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {

        Page<UserResponse> userAccounts = userService.getAllUser(pageNumber,pageSize);

        model.addAttribute("userAccounts", userAccounts.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", userAccounts.getTotalPages());
        return "admin/user-account";
    }

    @GetMapping("/program-manage")
    public String programManagePage()
    {
        return "admin/project";
    }


}
