package com.javaweb.controller;

import com.javaweb.exception.InvalidDataException;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.model.dto.LoginDTO;
import com.javaweb.model.response.TokenResponse;
import com.javaweb.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("modelLogin", new LoginDTO());
        model.addAttribute("token", new TokenResponse());
        return "users/login";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute("modelLogin") LoginDTO request, BindingResult bindingResult, Model model) {
        try{
            TokenResponse tokenResponse = authenticationService.authenticate(request);
            if (bindingResult.hasErrors()) {
                return "users/login";
            }
            if (tokenResponse.getUserId() != null) {
                model.addAttribute("token", tokenResponse);
            }
        } catch (ResourceNotFoundException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "users/login";
        } catch (AuthenticationException e){
            model.addAttribute("errorMessage", "Username or Password is incorrect");
        } catch (InvalidDataException e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "users/login";
    }
}
