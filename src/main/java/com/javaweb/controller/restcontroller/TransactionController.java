package com.javaweb.controller.restcontroller;


import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.TransactionAdminResponse;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.service.TransactionService;
import jakarta.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTransactions(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size)
            throws ParseException {
        Page<TransactionAdminResponse> transactions = transactionService.getAllTransactions(page, size);
        ResponseDTO a = ResponseDTO.builder().data(transactions).message("Lấy ra thành công").detail("OK").build();
        return ResponseEntity.ok(a);
    }
}
