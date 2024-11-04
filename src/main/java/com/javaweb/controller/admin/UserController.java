package com.javaweb.controller.admin;


import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/admin")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUsers(){
        ResponseDTO responseDTO = userService.getAllUser();
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
