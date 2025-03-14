package com.example.authservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User with this email doesn't exist");
    }
}
