package com.example.marketapp.service.impl;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.mapper.RequestMapper;
import com.example.marketapp.mapper.ResponseMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.repository.UserRepository;
import com.example.marketapp.service.ProductService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final RequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final ResponseMapper<ProductResponseDto, Product> productResponseMapper;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              RequestMapper<ProductRequestDto, Product> productRequestMapper,
                              ResponseMapper<ProductResponseDto, Product> productResponseMapper,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productResponseMapper.mapToDto(productRepository.save(
                productRequestMapper.mapToModel(productRequestDto)));
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return productResponseMapper.mapToDto(productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find product by id: " + id)));
    }

    @Override
    public ProductResponseDto updateProductById(Long id, ProductRequestDto productRequestDto) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find product by id: " + id));
        if (productRequestDto.getName() != null) {
            productToUpdate.setName(productRequestDto.getName());
        }
        if (productRequestDto.getPrice() != null) {
            productToUpdate.setPrice(productRequestDto.getPrice());
        }
        return productResponseMapper.mapToDto(productRepository.save(productToUpdate));
    }

    @Override
    public ProductResponseDto deleteProductById(Long id) {
        Product productToDelete = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete product by id: " + id));
        List<User> usersWhoHasBoughtProduct = userRepository.findAll().stream()
                .filter(e -> e.getProducts().contains(productRepository.getById(id)))
                .collect(Collectors.toList());
        for (int i = 0; i < usersWhoHasBoughtProduct.size(); i++) {
            Set<Product> products = usersWhoHasBoughtProduct.get(i).getProducts();
            products.remove(productToDelete);
            usersWhoHasBoughtProduct.get(i).setProducts(products);
            User userToUpdate = userRepository.save(usersWhoHasBoughtProduct.get(i));
            usersWhoHasBoughtProduct.set(i, userToUpdate);
        }
        productRepository.deleteById(id);
        return productResponseMapper.mapToDto(productToDelete);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByUserId(Long id) {
        User user = userRepository.getById(id);
        List<ProductResponseDto> products = user.getProducts().stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
        if (products.isEmpty()) {
            return null;
        }
        return products;
    }
}
