package com.example.marketapp.config;

import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final ProductService productService;

    public DataInitializer(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostConstruct
    public void inject() {
        Product water = new Product();
        water.setName("Water");
        water.setPrice(BigDecimal.valueOf(20));
        productService.createProduct(water);

        Product coffee = new Product();
        coffee.setName("Coffee");
        coffee.setPrice(BigDecimal.valueOf(200));
        productService.createProduct(coffee);

        Product tea = new Product();
        tea.setName("Tea");
        tea.setPrice(BigDecimal.valueOf(100));
        productService.createProduct(tea);

        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setAmountOfMoney(BigDecimal.valueOf(100));
        userService.createUser(john);

        User jane = new User();
        jane.setFirstName("Jane");
        jane.setLastName("Doe");
        jane.setAmountOfMoney(BigDecimal.valueOf(200));
        userService.createUser(jane);

        User mary = new User();
        mary.setFirstName("Mary");
        mary.setLastName("Sue");
        mary.setAmountOfMoney(BigDecimal.valueOf(50));
        userService.createUser(mary);
    }
}
