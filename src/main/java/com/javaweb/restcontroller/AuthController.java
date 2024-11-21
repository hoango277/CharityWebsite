package com.javaweb.restcontroller;

import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.TokenResponse;
import com.javaweb.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        String message = authenticationService.logout(request);

        Cookie authCookie = new Cookie("AUTH_TOKEN", null);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(0);

        response.addCookie(authCookie);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<StatusResponse> forgotPassword(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<StatusResponse>(authenticationService.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<StatusResponse> resetPassword(@RequestParam String secretKey){
        return new ResponseEntity<StatusResponse>(authenticationService.resetPassword(secretKey), HttpStatus.OK);
    }
}
