package com.javaweb.service.impl;

import com.javaweb.entity.TokenEntity;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.repository.TokenRepository;
import com.javaweb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;
}
