package com.example.productservice.service;

import com.example.productservice.dto.CategoryAddDto;
import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.mapper.CategoryMapper;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategory(String name){
        return categoryRepository.findByName(name);
    }

    public void addCategory(CategoryAddDto categoryAddDto){
        Category category = CategoryMapper.convertToEntity(categoryAddDto);
        categoryRepository.save(category);
    }
}
