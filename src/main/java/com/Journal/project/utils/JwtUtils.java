package com.Journal.project.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private String SECRET_KEY = "X5ZK/PaDcv6gV4FbLZnGj1bPqKmiRFXgQCRYyx6GE7g";

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpirations(String token){
        return extractAllClaims(token).getExpiration();
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token){
        return extractExpirations(token).before(new Date());
    }

    public Boolean validateToken(String token){
        return isTokenExpired(token);
    }

    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 50))
                .signWith(getSigningKey())
                .compact();
    }

}
