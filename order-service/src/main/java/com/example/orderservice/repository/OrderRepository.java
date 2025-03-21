package com.example.orderservice.repository;

import com.example.orderservice.entity.Ordeer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Ordeer, UUID> {
    boolean existsOrdeerByStatusAndUserId(String status, UUID userId);
    Ordeer findOrdeerByStatusAndUserId(String status, UUID userId);
    Optional<Ordeer> findOrdeerById(UUID orderId);
    Optional<Ordeer> deleteOrdeerById(UUID orderId);
}
