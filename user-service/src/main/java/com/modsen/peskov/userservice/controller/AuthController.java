package com.modsen.peskov.userservice.controller;

import com.modsen.peskov.userservice.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import com.modsen.peskov.userservice.dto.UserRegDto;
import com.modsen.peskov.userservice.entity.User;
import com.modsen.peskov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("http://localhost:5173")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    //private final AuthenticationManager authenticationManager;
    //private final JwtUtils jwtUtils;

    @PostMapping("/sign/up")
    public ResponseEntity<String> signUp(@RequestBody UserRegDto userRegDto){
        try{
            userService.signUp(userRegDto);
            return ResponseEntity.ok("Sign up was successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
