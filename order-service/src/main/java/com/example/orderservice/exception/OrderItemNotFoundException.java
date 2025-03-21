package com.example.orderservice.exception;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException() {
        super("Order item with this ID doesn't exists");
    }
}
