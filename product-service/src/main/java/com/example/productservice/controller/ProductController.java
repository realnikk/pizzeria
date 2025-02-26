package com.example.productservice.controller;

import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/add-product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductAddDto productAddDto){
        try{
            //HttpHeaders headers = new HttpHeaders();
            //headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
            //headers.add("Access-Control-Allow-Methods", "POST, OPTIONS");
            //headers.add("Access-Control-Allow-Headers", "Content-Type");
            //productService.addProduct(productAddDto);
            //return ResponseEntity.ok().headers(headers).body("Product added");
            return ResponseEntity.ok("The product was added successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/add/image")
//    public ResponseEntity<String> setProductImage(@RequestParam("file") MultipartFile file,
//                                                  @RequestParam("name") String name){
//        try{
//            productService.setProductImage(file, name);
//            return ResponseEntity.ok("The product image was added successfully!");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/all")
    public List<ProductAddDto> getAllProducts(){
        return productService.getAllProducts();
    }
}
