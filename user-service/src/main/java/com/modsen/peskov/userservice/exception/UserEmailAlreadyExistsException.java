package com.modsen.peskov.userservice.exception;

public class UserEmailAlreadyExistsException extends RuntimeException{
    public UserEmailAlreadyExistsException(){
        super("User with this email already exists");
    }
}
