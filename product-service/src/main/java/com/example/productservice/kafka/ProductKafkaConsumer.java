package com.example.productservice.kafka;

//import com.example.productservice.dto.CategoryDto;
//import com.example.productservice.dto.ProductPriceRequest;
//import com.example.productservice.dto.ProductPriceResponse;
//import com.example.productservice.entity.Category;
//import com.example.productservice.service.CategoryService;
//import com.example.productservice.service.ProductService;
//import lombok.AllArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
//@AllArgsConstructor
public class ProductKafkaConsumer {
//    private final ProductService productService;
//    private final CategoryService categoryService;
    //private final KafkaTemplate<String, ProductPriceResponse> kafkaTemplate;

//    @KafkaListener(topics = "topic3", groupId = "newgroup", containerFactory = "kafkaListenerContainerFactory")
//    public void consume(ProductPriceRequest request) {
        //double price = productService.getProductPrice(UUID.fromString(request.getId()));
        //ProductPriceResponse response = new ProductPriceResponse(request.getId(), price);
        //kafkaTemplate.send("product-price-response", response);
        //Category category = CategoryDto.builder().name("axyet"+String.valueOf(price));
//        categoryService.addCategory(CategoryDto.builder().name(request.getId()).build());
//    }
}