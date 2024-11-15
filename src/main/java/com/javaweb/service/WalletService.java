package com.javaweb.service;

import com.javaweb.model.dto.ResponseDTO;

import java.text.ParseException;

public interface WalletService {
    ResponseDTO getUserWallet(long userID) throws ParseException;
}
