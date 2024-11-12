package com.javaweb.service;

import com.javaweb.model.response.ResponseDTO;

public interface WalletService {
    ResponseDTO getUserWallet(long userID);
}
