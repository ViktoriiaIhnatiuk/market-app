package com.example.marketapp.service;

import com.example.marketapp.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product deleteProductById(Long id);
}
