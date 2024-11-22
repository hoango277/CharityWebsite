package com.javaweb.controller;

import com.javaweb.exception.InvalidDataException;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "users/register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("user") RegisterDTO registerDTO, BindingResult result, Model model) {
        try{
            if (result.hasErrors()) {
                return "users/register";
            }
            StatusResponse statusResponse = authenticationService.registerUser(registerDTO);
            if (statusResponse.getStatus() == HttpStatus.CREATED.value()) {
                model.addAttribute("successMessage", statusResponse.getMessage());
                return "redirect:/login";
            }
        } catch (InvalidDataException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "users/register";
        }
        return "users/register";
    }
}
