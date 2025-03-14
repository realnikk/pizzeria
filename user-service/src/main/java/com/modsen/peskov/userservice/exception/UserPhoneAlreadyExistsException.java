package com.modsen.peskov.userservice.exception;

public class UserPhoneAlreadyExistsException extends RuntimeException{
    public UserPhoneAlreadyExistsException(){
        super("User with this phone already exists");
    }
}
