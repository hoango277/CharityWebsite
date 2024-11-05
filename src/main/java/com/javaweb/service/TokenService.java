package com.javaweb.service;

import com.javaweb.entity.TokenEntity;

public interface TokenService {
    Long saveToken(TokenEntity tokenEntity);

    String deleteToken(Long tokenId);

    TokenEntity findByUserName(String name);

    TokenEntity getByName(String name);
}
