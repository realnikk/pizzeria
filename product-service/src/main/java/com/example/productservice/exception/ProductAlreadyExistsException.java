package com.example.productservice.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(){
        super("Product with this name already exists");
    }
}
