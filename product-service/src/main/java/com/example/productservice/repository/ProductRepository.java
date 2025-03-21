package com.example.productservice.repository;

import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory(Category category);
    boolean existsByName(String name);
}
