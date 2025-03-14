package com.example.productservice.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("Category with this name doesn't exists");
    }
}
