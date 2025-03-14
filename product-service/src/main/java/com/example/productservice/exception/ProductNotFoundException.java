package com.example.productservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product with this name doesn't exists");
    }
}
