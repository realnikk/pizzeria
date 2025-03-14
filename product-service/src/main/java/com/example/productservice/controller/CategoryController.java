package com.example.productservice.controller;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.exception.CategoryAlreadyExistsException;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            return ResponseEntity.ok(categoryService.addCategory(categoryDto));
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateCategory(@PathVariable String name,
                                            @RequestBody CategoryDto categoryDto) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(name, categoryDto));

        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable String name) {
        try {
            categoryService.deleteCategory(name);
            return ResponseEntity.ok(200);

        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
