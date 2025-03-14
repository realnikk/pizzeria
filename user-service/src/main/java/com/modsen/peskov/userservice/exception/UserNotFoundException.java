package com.modsen.peskov.userservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User with this email doesn't exists");
    }
}