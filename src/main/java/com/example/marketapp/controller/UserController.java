package com.example.marketapp.controller;

import com.example.marketapp.dto.request.ProductRequestDto;
import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.ProductResponseDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.mapper.RequestMapper;
import com.example.marketapp.mapper.ResponseMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RequestMapper<UserRequestDto, User> userRequestMapper;

    public UserController(UserService userService, ProductService productService,
                          RequestMapper<UserRequestDto, User> userRequestMapper,
                          ResponseMapper<UserResponseDto, User> userResponseMapper,
                          RequestMapper<ProductRequestDto, Product> productRequestMapper,
                          ResponseMapper<ProductResponseDto, Product> productResponseMapper) {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestMapper.mapToModel(userRequestDto));
    }

    @GetMapping
    @ApiOperation(value = "Get a list of all users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a user by id")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user by id")
    public UserResponseDto updateUser(@PathVariable Long id,
                                           @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUserById(id, userRequestMapper.mapToModel(userRequestDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user by id")
    public UserResponseDto deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}/buy")
    @ApiOperation(value = "Update a user after buying a product")
    public String buyProduct(@PathVariable Long id,
                                      @RequestParam Long productId) {
        userService.buyProduct(id, productId);
        return "Successful purchase!";
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Get a list of users, that bought product, by product id")
    public List<UserResponseDto> getAllUsersByProductId(@PathVariable Long id) {
        return userService.getAllUsersByProductId(id);
    }
}
