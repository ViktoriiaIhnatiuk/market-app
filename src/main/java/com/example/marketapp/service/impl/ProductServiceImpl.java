package com.example.marketapp.service.impl;

import com.example.marketapp.model.Product;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find product by id: " + id));
    }

    @Override
    public Product deleteProductById(Long id) {
        Product productToDelete = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete product by id: " + id));
        return productToDelete;
    }
}
