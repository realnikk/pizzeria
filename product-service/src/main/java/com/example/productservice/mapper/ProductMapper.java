package com.example.productservice.mapper;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static Product convertToEntity(String name, String description, Category category, Double price, String imagePath) {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .imagePath(imagePath)
                .count(0)
                .build();
    }

    public static ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .imagePath(product.getImagePath())
                .build();
    }
}
