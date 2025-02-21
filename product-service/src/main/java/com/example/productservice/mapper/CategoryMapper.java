package com.example.productservice.mapper;

import com.example.productservice.dto.CategoryAddDto;
import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static Category convertToEntity(CategoryAddDto categoryAddDto) {
        return Category.builder()
                .name(categoryAddDto.getName())
                .build();
    }
}
