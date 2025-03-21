package com.example.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order with this ID doesn't exists");
    }
}
