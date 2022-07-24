package com.example.marketapp.service;

import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.model.Product;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(Product product);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    ProductResponseDto updateProductById(Long id, Product product);

    ProductResponseDto deleteProductById(Long id);

    List<ProductResponseDto> getAllProductsByUserId(Long id);
}
