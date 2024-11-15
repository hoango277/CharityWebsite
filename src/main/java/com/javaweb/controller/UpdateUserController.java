package com.javaweb.controller;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("${api.prefix}/user/update-user")
public class UpdateUserController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{userId}")
    public String showUpdateUserForm(Model model, @PathVariable String userId) {
        UserDTO userDTO = authenticationService.getUserById(Long.parseLong(userId));
        model.addAttribute("userUpdate", userDTO);
        return "users/update-user";
    }

    @PostMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,
                             @Valid @ModelAttribute("userDTO") UserDTO userDTO,
                             BindingResult bindingResult,
                             Model model){
        ResponseDTO responseDTO = authenticationService.updateUser(userId, userDTO);
        if(bindingResult.hasErrors()){
            return "users/update-user";
        }
        model.addAttribute("userDTO", userDTO);
        return "users/info-user";
    }

}
