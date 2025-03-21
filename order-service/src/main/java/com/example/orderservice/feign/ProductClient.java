package com.example.orderservice.feign;

import com.example.orderservice.dto.ProductPriceRequest;
import com.example.orderservice.dto.ProductPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product/price/{id}")
    ProductPriceResponse getProductPrice(@PathVariable String id);
}
