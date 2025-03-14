package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.ProductAlreadyExistsException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestPart("name") String name,
                                        @RequestPart("description") String description,
                                        @RequestPart("category") String category,
                                        @RequestPart("price") String price,
                                        @RequestPart("photo") MultipartFile photo){
        try{
            return ResponseEntity.ok(productService.addProduct(name, description, category, price, photo));
        } catch(ProductAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch(IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable String category){
        try{
            return productService.getProductsByCategory(category);
        } catch(CategoryNotFoundException e){
            return new ArrayList<>();
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateProduct(@PathVariable String name,
                                 @RequestParam("name") String newName,
                                 @RequestParam("description") String description,
                                 @RequestParam("category") String category,
                                 @RequestParam("price") Double price,
                                 @RequestParam("photo") MultipartFile photo){
        try{
            return ResponseEntity.ok(productService.updateProduct(name, newName, description, category, price, photo));
        } catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(ProductAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProduct(@PathVariable String name) {
        try {
            productService.deleteProduct(name);
            return ResponseEntity.ok(200);

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
