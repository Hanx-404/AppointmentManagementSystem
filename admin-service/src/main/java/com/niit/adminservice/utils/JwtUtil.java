package com.niit.adminservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    // 添加一个初始化方法
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512 );
    }
    public String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration *1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub",username);
        claims.put("iat",now);
        claims.put("exp",expiryDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // 验证token是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getExpiration(){
        return expiration;
    }


}
