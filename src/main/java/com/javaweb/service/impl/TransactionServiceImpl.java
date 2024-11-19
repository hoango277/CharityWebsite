package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;


    @Override
    public ResponseDTO getAllTransactions() throws ParseException {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity transaction : transactions) {
            TransactionResponse transactionResponse = transactionConverter.convertToResponse(transaction);
            transactionResponses.add(transactionResponse);
        }
        return ResponseDTO.builder().data(transactionResponses).message("Lấy tất cả transaction thành công")
                .detail("OK").build();
    }
}
