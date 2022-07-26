package com.example.marketapp.config;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.request.UserRequestDto;
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
        ProductRequestDto water = new ProductRequestDto();
        water.setName("Water");
        water.setPrice(BigDecimal.valueOf(20));
        productService.createProduct(water);

        ProductRequestDto coffee = new ProductRequestDto();
        coffee.setName("Coffee");
        coffee.setPrice(BigDecimal.valueOf(200));
        productService.createProduct(coffee);

        ProductRequestDto tea = new ProductRequestDto();
        tea.setName("Tea");
        tea.setPrice(BigDecimal.valueOf(100));
        productService.createProduct(tea);

        UserRequestDto john = new UserRequestDto();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setAmountOfMoney(BigDecimal.valueOf(800));
        userService.createUser(john);

        UserRequestDto jane = new UserRequestDto();
        jane.setFirstName("Jane");
        jane.setLastName("Doe");
        jane.setAmountOfMoney(BigDecimal.valueOf(300));
        userService.createUser(jane);

        UserRequestDto mary = new UserRequestDto();
        mary.setFirstName("Mary");
        mary.setLastName("Sue");
        mary.setAmountOfMoney(BigDecimal.valueOf(150));
        userService.createUser(mary);
    }
}
