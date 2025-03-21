package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Ordeer;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.exception.OrderItemNotFoundException;
import com.example.orderservice.feign.ProductClient;
import com.example.orderservice.kafka.OrderKafkaProducer;
import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final ProductClient productClient;
    private final JwtUtil jwtUtil;
    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    public OrderItem createOrderItem(String authHeader, OrderItemDto orderItemDto){
        String jwt = authHeader.substring(7);
        UUID userId = jwtUtil.getUserIdFromToken(jwt);
        System.out.println(userId);
        Ordeer order;
        if(orderService.existsOrderInStatusCart(userId)){
            order = orderService.getOrderInStatusCart(userId);
        } else {
            order = orderService.createOrder(authHeader);
        }
        OrderItem orderItem = OrderItemMapper.convertToEntity(order, orderItemDto);
        Double price = productClient.getProductPrice(String.valueOf(orderItemDto.getProductId())).getPrice();
        orderService.updateOrderPrice(order.getId(), price * orderItemDto.getCount());
        return orderItemRepository.save(orderItem);
    }

//    public void deleteOrderItems(Ordeer ordeer){
//        orderItemRepository.deleteOrderItemsByOrdeer(ordeer);
//    }

    @Transactional
    public void deleteOrderItem(String id){
        UUID orderItemId = UUID.fromString(id);
        orderItemRepository.deleteOrderItemById(orderItemId)
                .orElseThrow(OrderItemNotFoundException::new);
    }
}
