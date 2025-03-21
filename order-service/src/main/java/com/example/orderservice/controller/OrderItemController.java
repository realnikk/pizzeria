package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.exception.OrderItemNotFoundException;
import com.example.orderservice.service.OrderItemService;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/item")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestHeader("Authorization") String authHeader,
                                         @RequestBody OrderItemDto orderItemDto) {
        try {
            return ResponseEntity.ok(orderItemService.createOrderItem(authHeader, orderItemDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable String id) {
        try {
            orderItemService.deleteOrderItem(id);
            return ResponseEntity.ok(200);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
