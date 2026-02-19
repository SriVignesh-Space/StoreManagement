package com.storemanagement.vinyl.Utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class JwtUtil {
    private final long EXPIRATION = 1000*60*60;
    private final String secret_key = "my-super-secret-key-my-super-secret-key";
    private final SecretKey key = Keys.hmacShaKeyFor(secret_key.getBytes());

    public String generateToken(String email){
        return Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() * EXPIRATION))
                    .signWith(key)
                    .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(String token){
        Claims claim = extractClaims(token);
        return claim.getSubject();
    }

    private boolean isExpired(String token){
        Claims claims = extractClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username, UserDetails userdetails){
        System.out.println(username.equals(userdetails.getUsername()));
        System.out.println(isExpired(token));
        return username.equals(userdetails.getUsername()) && !isExpired(token);
    }
}
