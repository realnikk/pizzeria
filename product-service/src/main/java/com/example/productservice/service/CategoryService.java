package com.example.productservice.service;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.Category;
import com.example.productservice.exception.CategoryAlreadyExistsException;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.mapper.CategoryMapper;
import com.example.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategory(String name){
        return categoryRepository.findByName(name)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public Category addCategory(CategoryDto categoryDto){
        if(categoryRepository.existsByName(categoryDto.getName())){
            throw new CategoryAlreadyExistsException();
        }
        Category category = CategoryMapper.convertToEntity(categoryDto);
        return categoryRepository.save(category);
    }

    public Category updateCategory(String name, CategoryDto categoryDto){
        Category category = categoryRepository.findByName(name)
                .orElseThrow(CategoryNotFoundException::new);
        if(categoryRepository.existsByName(categoryDto.getName())){
            throw new CategoryAlreadyExistsException();
        }
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(String name){
        Category category = categoryRepository.findByName(name)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }

    public List<CategoryDto> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::convertToDto).toList();
    }
}
