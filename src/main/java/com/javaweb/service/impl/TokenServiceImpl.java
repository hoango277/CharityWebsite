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

    @Override
    public Long saveToken(TokenEntity tokenEntity) {
        Optional<TokenEntity> token = tokenRepository.findByName(tokenEntity.getName());
        if (token.isEmpty()){
            tokenRepository.save(tokenEntity);
            return tokenEntity.getId();
        } else {
            TokenEntity currentToken = token.get();
            currentToken.setAccessToken(tokenEntity.getAccessToken());
            currentToken.setRefreshToken(tokenEntity.getRefreshToken());
            tokenRepository.save(currentToken);
            return tokenEntity.getId();
        }
    }

    @Override
    public String deleteToken(Long tokenId) {
        tokenRepository.deleteById(tokenId);
        return "Deleted!";
    }

    @Override
    public TokenEntity findByUserName(String name) {
        return tokenRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Token not exist"));
    }

    @Override
    public TokenEntity getByName(String name) {
        return tokenRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Token not exist"));
    }
}
