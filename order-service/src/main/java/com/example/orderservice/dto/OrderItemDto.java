package com.example.orderservice.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItemDto {
    private Integer count;
    private UUID productId;
}
