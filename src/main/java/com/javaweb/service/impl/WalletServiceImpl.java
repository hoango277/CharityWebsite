package com.javaweb.service.impl;

import com.javaweb.repository.WalletRepository;
import com.javaweb.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;
}
