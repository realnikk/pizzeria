package com.example.authservice.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(){
        super("Incorrect email or password");
    }
}