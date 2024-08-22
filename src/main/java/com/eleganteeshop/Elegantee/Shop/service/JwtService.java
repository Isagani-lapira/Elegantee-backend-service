package com.eleganteeshop.Elegantee.Shop.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey =Jwts.SIG.HS256.key().build();
    private final Date expirationDate =Date.from(Instant.now().plusSeconds(60*59*3)); //3 hours


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(expirationDate)
                .issuer("Elegantee")
                .signWith(secretKey)
                .compact();
    }

    String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

     boolean isTokenValid(String token){
        return extractClaims(token).getExpiration().after(Date.from(Instant.now()));
    }
}
