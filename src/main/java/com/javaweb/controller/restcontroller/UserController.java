package com.javaweb.controller.restcontroller;


import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.AuthenticationService;
import com.javaweb.service.UploadImage;
import com.javaweb.service.UserService;
import com.javaweb.utils.MessageUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getInfoUser(@PathVariable String userId) throws ParseException {
        ResponseDTO responseDTO = userService.getUserById(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long userId,@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = authenticationService.updateUser(userId,userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/admin/account/{userId}")
    public ResponseEntity<StatusResponse> deleteUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
    }
}
