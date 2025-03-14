package com.example.productservice.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(){
        super("This category already exists");
    }
}
