package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.WalletEntity;
import com.javaweb.model.dto.ResponseDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.model.response.WalletResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.WalletRepository;
import com.javaweb.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public ResponseDTO getUserWallet(long userID) throws ParseException {
        WalletEntity walletEntity = walletRepository.findByUserID(userID);
        WalletResponse walletResponse = new WalletResponse();
        Optional<List<TransactionEntity>> walletTransaction = transactionRepository.findByUserID(userID);
        List<TransactionResponse> transactionResponse = new ArrayList<>();
        if (walletTransaction.isPresent()) {
            for(TransactionEntity i : walletTransaction.get()) {
                TransactionResponse transactionResponse1 = transactionConverter.convertToResponse(i);
                transactionResponse.add(transactionResponse1);
            }
            walletResponse.setList(transactionResponse);
        }
        walletResponse.setTotalAmount(walletEntity.getTotalAmount());
        return ResponseDTO.builder()
                .data(walletResponse)
                .message("Get wallet info of user " + walletEntity.getUser().getUsername() + " succeeded")
                .detail("Find ok").build();
    }
}
