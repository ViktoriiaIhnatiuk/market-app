package com.example.marketapp.service;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.model.Product;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto);

    ProductResponseDto deleteProductById(Long id);

    List<ProductResponseDto> getAllProductsByUserId(Long id);
}
