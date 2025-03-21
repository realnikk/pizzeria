package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ordeer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private LocalTime creationTime;
    private LocalDate creationDate;
    private String paymentMethod;
    private String address;
    private String status;
    private Double price;
    private UUID userId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
}
