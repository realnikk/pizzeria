package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.CategoryAlreadyExistsException;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.ProductAlreadyExistsException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.mapper.CategoryMapper;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final String IMAGE_DIR = "D:/product_images/";
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product addProduct(String name, String description, String category, String price, MultipartFile photo) throws IOException {
        if(productRepository.existsByName(name)){
            throw new ProductAlreadyExistsException();
        }
        String imagePath = UUID.randomUUID() + ".jpg";
        File imageFile = new File(IMAGE_DIR + imagePath);
        photo.transferTo(imageFile);
        Product product = ProductMapper.convertToEntity(
                name,
                description,
                categoryService.findCategory(category),
                Double.parseDouble(price),
                imagePath
        );
        return productRepository.save(product);
    }

    public List<ProductDto> getProducts() {
        List<Product> products =productRepository.findAll();
        return products.stream()
                .map(ProductMapper::convertToDto).toList();
    }

    public List<ProductDto> getProductsByCategory(String categoryName) {
        Category category = categoryService.findCategory(categoryName);
        List<Product> products =productRepository.findAllByCategory(category);
        return products.stream()
                .map(ProductMapper::convertToDto).toList();
    }

    public Product updateProduct(String name, String newName, String description, String categoryName, Double price, MultipartFile photo) throws IOException {
        Product product = productRepository.findByName(name)
                .orElseThrow(ProductNotFoundException::new);
        if(productRepository.existsByName(newName)){
            throw new ProductAlreadyExistsException();
        }
        product.setName(newName);
        product.setDescription(description);
        product.setPrice(price);
        Category category = categoryService.findCategory(categoryName);
        product.setCategory(category);
        String imagePath = UUID.randomUUID() + ".jpg";
        File imageFile = new File(IMAGE_DIR + imagePath);
        photo.transferTo(imageFile);
        product.setImagePath(imagePath);
        return productRepository.save(product);
    }

    public void deleteProduct(String name){
        Product product = productRepository.findByName(name)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }



//    public void setProductImage(MultipartFile file, String name) throws IOException {
//        String filename = UUID.randomUUID() + ".jpg";
//        File imageFile = new File(IMAGE_DIR + filename);
//
//        file.transferTo(imageFile);
//
//        Product product = productRepository.findByName(name);
//        product.setImagePath(filename);
//        productRepository.save(product);
//    }
}
