package com.secondlife.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt-secret}")
    private String SALT;

    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .claim(claimId, data)
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }
}
