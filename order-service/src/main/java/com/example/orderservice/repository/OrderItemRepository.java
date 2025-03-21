package com.example.orderservice.repository;

import com.example.orderservice.entity.Ordeer;
import com.example.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    Optional<OrderItem> deleteOrderItemById(UUID id);
    //void deleteOrderItemsByOrdeer(Ordeer order);
}
