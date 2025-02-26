package com.example.orderservice.controller;

import com.example.orderservice.service.OrderItemService;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("http://localhost:5173")
@RequestMapping("/order/item")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
}
