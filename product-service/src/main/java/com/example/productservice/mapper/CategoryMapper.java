package com.example.productservice.mapper;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static Category convertToEntity(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }
    public static CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }
}
