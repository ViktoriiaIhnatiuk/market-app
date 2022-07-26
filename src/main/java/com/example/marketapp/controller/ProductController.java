package com.example.marketapp.controller;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new product")
    public ProductResponseDto createProduct(@Valid @RequestBody
                                                ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @GetMapping
    @ApiOperation(value = "Get a list of all products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product by id")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a product by id")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                              @Valid @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProductById(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product by id")
    public ProductResponseDto deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("users/{id}")
    @ApiOperation(value = "Get a list of user's products by user's id")
    public List<ProductResponseDto> getAllProductsByUserId(@PathVariable Long id) {
        return productService.getAllProductsByUserId(id);
    }

}
