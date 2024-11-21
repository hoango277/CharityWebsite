package com.javaweb.utils;

import com.javaweb.exception.InvalidDataException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.javaweb.utils.TokenType.*;

@Component
@RequiredArgsConstructor
public class JWTTokenUtils {
    @Value("${jwt.expirationHour}")
    private int expirationHour;

    @Value("${jwt.expirationDay}")
    private int expirationDay;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    @Value("${jwt.resetKey}")
    private String resetKey;

    public String generateToken(UserDetails userDetails,Long userId){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return generateToken(claims, userDetails);
    }

    private String generateToken(Map<String, Object> claims,UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationHour * 1000L))
                .signWith(getSignInKey(ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey(TokenType type) {
        switch (type){
            case ACCESS_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));}
            case REFRESH_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));}
            case RESET_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(resetKey));}
            default -> throw new InvalidDataException("Token type not supported");
        }
    }

    private Claims extractAllClaims(String token, TokenType type) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractClaim(String token, TokenType type, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, type);
        return claimsResolver.apply(claims);
    }

    //check expiration
    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token,type).before(new Date());
    }

    private Date extractExpiration(String token, TokenType type) {
        return extractClaim(token, type, Claims::getExpiration);
    }

    public String extractUser(String token, TokenType type) {
        return extractClaim(token, type, Claims::getSubject);
    }

    public boolean validateToken(String token,TokenType type, UserDetails userDetails) {
        String user = extractUser(token,type);
        return (user.equals(userDetails.getUsername()))
                && !isTokenExpired(token,type);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    private String generateRefreshToken(Map<String, Object> claims,UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationDay * 1000L))
                .signWith(getSignInKey(REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateResetToken(UserDetails userDetails) {
        return generateResetToken(new HashMap<>(), userDetails);
    }

    private String generateResetToken(Map<String, Object> claims,UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000L))
                .signWith(getSignInKey(RESET_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }
}
