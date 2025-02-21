package com.example.productservice.service;

import com.example.productservice.dto.ProductAddDto;
import com.example.productservice.entity.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final String IMAGE_DIR = "D:/product_images/";
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public void addProduct(ProductAddDto productAddDto) throws IOException {
        Product product = ProductMapper.convertToEntity(
                categoryService.findCategory(productAddDto.getCategory()),
                productAddDto
        );
        productRepository.save(product);
    }

    public void setProductImage(MultipartFile file, String name) throws IOException {
        String filename = UUID.randomUUID() + ".jpg";
        File imageFile = new File(IMAGE_DIR + filename);

        file.transferTo(imageFile);

        Product product = productRepository.findByName(name);
        product.setImagePath(filename);
        productRepository.save(product);
    }
}
