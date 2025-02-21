package com.example.productservice.dto;

import com.example.productservice.entity.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class ProductAddDto {
    private String name;
    private String description;
    private Double price;
    private String category;
}
