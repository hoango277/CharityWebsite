package com.javaweb.service;

import com.javaweb.model.response.TransactionAdminResponse;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface TransactionService {
    public Page<TransactionAdminResponse> getAllTransactions(int page, int size) throws ParseException;
}
