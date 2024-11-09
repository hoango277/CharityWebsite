package com.javaweb.controller.restcontroller;

import com.javaweb.model.dto.ResetPasswordDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.TokenResponse;
import com.javaweb.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request){
        return new ResponseEntity<TokenResponse>(authenticationService.refresh(request), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        return new ResponseEntity<String>(authenticationService.logout(request), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<StatusResponse> forgotPassword(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<StatusResponse>(authenticationService.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<StatusResponse> resetPassword(@RequestParam String secretKey){
        return new ResponseEntity<StatusResponse>(authenticationService.resetPassword(secretKey), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<StatusResponse> changePassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return new ResponseEntity<StatusResponse>(authenticationService.changePassword(resetPasswordDTO), HttpStatus.OK);
    }
}
