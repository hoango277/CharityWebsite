package com.javaweb.controller.admin;


import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.AuthenticationService;
import com.javaweb.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/admin")
    public ResponseEntity<ResponseDTO> getAllUsers(){
        ResponseDTO responseDTO = userService.getAllUser();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getInfoUser(@PathVariable Long userId) {
        ResponseDTO responseDTO = userService.getUserById(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

//    @GetMapping("/user/{userId}")
//    public String getInfoUser(@PathVariable Long userId, Model model) {
//        ResponseDTO responseDTO = userService.getUserById(userId);
//        model.addAttribute("infoUser", responseDTO.getData()); // Thêm dữ liệu vào Model với key là "user"
//        return "html"; // Tên của view Thymeleaf (tệp .html)
//    }

//    @GetMapping("/user/{userId}")
//    public String formUpdateUser(Model model,@PathVariable Long userId) {
//        UserDTO userDTO = authenticationService.getUserById(userId);
//        model.addAttribute("updateUser", userDTO);
//        return "the.html";
//    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long userId,@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = authenticationService.updateUser(userId,userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<StatusResponse> deleteUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
    }
}
