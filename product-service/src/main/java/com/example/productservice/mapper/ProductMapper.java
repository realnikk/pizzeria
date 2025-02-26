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
                .price(Integer.parseInt(productAddDto.getPrice()))
                .category(category)
                .build();
    }

    public static ProductAddDto convertToDto(Product product) {
        ProductAddDto productAddDto = new ProductAddDto();
        productAddDto.setName(product.getName());
        productAddDto.setDescription(product.getDescription());
        productAddDto.setPrice(String.valueOf(product.getPrice()));
        productAddDto.setCategory(product.getCategory().getName());
        return productAddDto;
    }
}
