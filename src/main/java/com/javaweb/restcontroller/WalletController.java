package com.javaweb.restcontroller;


import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/wallet")
@Slf4j
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/{userID}")
    public ResponseEntity<ResponseDTO> getUserWallet(@PathVariable long userID) throws ParseException {
        ResponseDTO walletResponse = walletService.getUserWallet(userID);
        return new ResponseEntity<>(walletResponse,HttpStatus.OK);
    }
}


