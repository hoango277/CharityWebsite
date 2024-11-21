package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.response.TransactionAdminResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;


    @SneakyThrows
    @Override
    public Page<TransactionAdminResponse> getAllTransactions(int page , int size) throws ParseException {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionEntity> transactions = transactionRepository.findAllTransaction(pageable);
        return transactions.map(transactionEntity -> {
            try {
                return transactionConverter.convertToResponseAdmin(transactionEntity);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing volunteer entity", e);
            }
        });

    }
}
