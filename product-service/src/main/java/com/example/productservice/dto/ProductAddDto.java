package com.example.productservice.dto;

import com.example.productservice.entity.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddDto {
    private String name;
    private String description;
    private String category;
    private String price;
}
