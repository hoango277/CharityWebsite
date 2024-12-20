package com.javaweb.converter;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.response.TransactionAdminResponse;
import com.javaweb.model.response.TransactionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    private String formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public TransactionResponse convertToResponse(TransactionEntity transactionEntity) throws ParseException {
        TransactionResponse transactionResponse = modelMapper.map(transactionEntity, TransactionResponse.class);
        transactionResponse.setTransactionDate(formatDate(transactionEntity.getTransactionDate()));
        return transactionResponse;
    }

    public TransactionAdminResponse convertToResponseAdmin(TransactionEntity transactionEntity) throws ParseException {
        TransactionAdminResponse transactionResponse = modelMapper.map(transactionEntity, TransactionAdminResponse.class);
        transactionResponse.setTransactionDate(formatDate(transactionEntity.getTransactionDate()));
        transactionResponse.setUsername(transactionEntity.getUser().getUsername());
        return transactionResponse;
    }
}
