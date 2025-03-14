package com.example.authservice.controller;

import com.example.authservice.dto.SignInDto;
import com.example.authservice.exception.IncorrectPasswordException;
import com.example.authservice.exception.UserNotFoundException;
import com.example.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        //Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        try{
            return ResponseEntity.ok(authService.generateToken(signInDto));
        } catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(IncorrectPasswordException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        try{
            authService.validateToken(token);
            return "Token is valid";
        } catch(Exception e){
            return "Token not valid";
        }

    }
}