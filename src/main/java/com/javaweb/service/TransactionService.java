package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.TransactionResponse;

import java.text.ParseException;
import java.util.List;

public interface TransactionService {
    ResponseDTO getAllTransactions() throws ParseException;
}
