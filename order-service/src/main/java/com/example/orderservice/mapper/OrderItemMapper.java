package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Ordeer;
import com.example.orderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
public class OrderItemMapper {
    public static OrderItem convertToEntity(Ordeer order, OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .count(orderItemDto.getCount())
                .productId(orderItemDto.getProductId())
                .order(order)
                .build();
    }
}
