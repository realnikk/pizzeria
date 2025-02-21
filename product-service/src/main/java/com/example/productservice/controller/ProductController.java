package com.example.productservice.controller;

import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@CrossOrigin("http://localhost:5173")
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductAddDto productAddDto){
        try{
            productService.addProduct(productAddDto);
            return ResponseEntity.ok("The product was added successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add/image")
    public ResponseEntity<String> setProductImage(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("name") String name){
        try{
            productService.setProductImage(file, name);
            return ResponseEntity.ok("The product image was added successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
