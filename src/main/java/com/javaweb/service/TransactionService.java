package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.TransactionAdminResponse;
import com.javaweb.model.response.TransactionResponse;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface TransactionService {
    public Page<TransactionAdminResponse> getAllTransactions(int page, int size) throws ParseException;
}
