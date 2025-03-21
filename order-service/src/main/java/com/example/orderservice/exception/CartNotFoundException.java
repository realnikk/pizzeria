package com.example.orderservice.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("You don't have order items for publishing!");
    }
}
