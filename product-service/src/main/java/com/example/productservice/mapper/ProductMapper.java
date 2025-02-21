package com.example.productservice.mapper;

import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static Product convertToEntity(Category category, ProductAddDto productAddDto) {
        return Product.builder()
                .name(productAddDto.getName())
                .description(productAddDto.getDescription())
                .price(productAddDto.getPrice())
                .category(category)
                .build();
    }
}
