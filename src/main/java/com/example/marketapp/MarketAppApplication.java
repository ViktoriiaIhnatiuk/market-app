package com.example.marketapp;

import com.example.marketapp.controller.ProductController;
import com.example.marketapp.controller.UserController;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketAppApplication {
//private static UserService userService;
//private static ProductService productService;


    public static void main(String[] args) {
        SpringApplication.run(MarketAppApplication.class, args);
//        User testUser = userService.getUserById(2L);
//
//        Product product = productService.getProductById(2L);
//
//        System.out.println(userService.buyProduct(testUser, product));
    }



}
