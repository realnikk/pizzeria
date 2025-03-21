package com.example.orderservice.mapper;

import com.example.orderservice.entity.Ordeer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
public class OrderMapper {
    public static Ordeer convertToEntity(UUID userId) {
        return Ordeer.builder()
                .status("CART")
                .price(0.0)
                .userId(userId)
                .build();
    }
}
