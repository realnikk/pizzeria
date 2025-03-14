package com.example.authservice.service;

import com.example.authservice.feign.UserClient;
import com.example.authservice.dto.SignInDto;
import com.example.authservice.dto.UserSecurityDto;
import com.example.authservice.exception.IncorrectPasswordException;
import com.example.authservice.exception.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Value("${auth.token.jwtSecret}")
    private String SECRET;
    @Value("${auth.token.expirationInMils}")
    private int JWT_EXPIRATION_MS;

//    @Autowired
//    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserClient userClient;

//    public String saveUser(UserCredential credential) {
//        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
//        repository.save(credential);
//        return "user added to the system";
//    }


    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(SignInDto signInDto) {
        UserSecurityDto userSecurityDto = userClient.getUserByEmail(signInDto.getEmail());
        if(userSecurityDto.getEmail().isEmpty()){
            throw new UserNotFoundException();
        }
        if(!passwordEncoder.matches(signInDto.getPassword(), userSecurityDto.getPassword())){
            throw new IncorrectPasswordException();
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userSecurityDto.getEmail());
        claims.put("role", userSecurityDto.getRole());
        return createToken(claims, userSecurityDto.getId());
    }

    private String createToken(Map<String, Object> claims, String userId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}