package com.example.marketapp.controller;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.mapper.RequestMapper;
import com.example.marketapp.mapper.ResponseMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final RequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final ResponseMapper<ProductResponseDto, Product> productResponseMapper;

    public ProductController(ProductService productService,
                             UserService userService, RequestMapper<ProductRequestDto, Product> productRequestMapper,
                             ResponseMapper<ProductResponseDto, Product> productResponseMapper) {
        this.productService = productService;
        this.userService = userService;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.mapToModel(productRequestDto);
        Product saved = productService.createProduct(product);
        ProductResponseDto productResponseDto = productResponseMapper.mapToDto(saved);
        return productResponseDto;
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts().stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productResponseMapper.mapToDto(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.getProductById(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        Product updatedProduct = productService.createProduct(product);
        return productResponseMapper.mapToDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteProductById(@PathVariable Long id) {
        Product productToDelete = productService.getProductById(id);
        return productResponseMapper.mapToDto(productToDelete);
    }

    @GetMapping("users/{id}")
    public List<ProductResponseDto> getAllProductsByUserId(@PathVariable Long id) {
        User user = userService.getUserById(id);
        List<ProductResponseDto> products = user.getProducts().stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
        return products;
    }

}
