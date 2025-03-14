package com.example.productservice.repository;

import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory(Category category);
    boolean existsByName(String name);
}
