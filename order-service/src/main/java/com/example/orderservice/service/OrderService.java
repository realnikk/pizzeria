package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Ordeer;
import com.example.orderservice.exception.CartNotFoundException;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final JwtUtil jwtUtil;
    private final OrderRepository orderRepository;
    //private final OrderItemRepository orderItemRepository;
    public Ordeer createOrder(String authHeader){
        String jwt = authHeader.substring(7);
        UUID userId = jwtUtil.getUserIdFromToken(jwt);
        Ordeer order = OrderMapper.convertToEntity(userId);
        return orderRepository.save(order);
    }

    public boolean existsOrderInStatusCart(UUID userId){
        return orderRepository.existsOrdeerByStatusAndUserId("CART", userId);
    }

    public Ordeer getOrderInStatusCart(UUID userId){
        return orderRepository.findOrdeerByStatusAndUserId("CART", userId);
    }

    public Ordeer publishOrder(String authHeader, OrderDto orderDto){
        String jwt = authHeader.substring(7);
        UUID userId = jwtUtil.getUserIdFromToken(jwt);
        Ordeer order;
        if(orderRepository.existsOrdeerByStatusAndUserId("CART", userId)){
            order = orderRepository.findOrdeerByStatusAndUserId("CART", userId);
        } else{
            throw new CartNotFoundException();
        }
        order.setStatus("CREATED");
        order.setAddress(orderDto.getAddress());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setCreationDate(LocalDate.now());
        order.setCreationTime(LocalTime.now());
        return orderRepository.save(order);
    }

    public Ordeer deliverOrder(String id){
        UUID orderId = UUID.fromString(id);
        Ordeer order = orderRepository.findOrdeerById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        order.setStatus("DELIVERED");
        return orderRepository.save(order);
    }

    public Ordeer updateOrderPrice(UUID orderId, Double price){
        //UUID orderId = UUID.fromString(id);
        Ordeer order = orderRepository.findOrdeerById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        Double currentPrice = order.getPrice();
        order.setPrice(currentPrice + price);
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(String id){
        UUID orderId = UUID.fromString(id);
//        Ordeer ordeer = orderRepository.findOrdeerById(orderId)
//                .orElseThrow(OrderNotFoundException::new);
        //orderItemRepository.deleteOrderItemsByOrdeer(ordeer);
        orderRepository.deleteOrdeerById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }
}
