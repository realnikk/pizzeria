package com.example.productservice.controller;

import com.example.productservice.dto.CategoryAddDto;
import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.service.CategoryService;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@CrossOrigin("http://localhost:5173")
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryAddDto categoryAddDto){
        try{
            categoryService.addCategory(categoryAddDto);
            return ResponseEntity.ok("The category was added successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
