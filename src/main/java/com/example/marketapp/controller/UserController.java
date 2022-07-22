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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final RequestMapper<UserRequestDto, User> userRequestMapper;
    private final ResponseMapper<UserResponseDto, User> userResponseMapper;
    private final RequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final ResponseMapper<ProductResponseDto, Product> productResponseMapper;

    public UserController(UserService userService, ProductService productService,
                          RequestMapper<UserRequestDto, User> userRequestMapper,
                          ResponseMapper<UserResponseDto, User> userResponseMapper,
                          RequestMapper<ProductRequestDto, Product> productRequestMapper,
                          ResponseMapper<ProductResponseDto, Product> productResponseMapper) {
        this.userService = userService;
        this.productService = productService;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
    }


    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userResponseMapper.mapToDto(userService.createUser(userRequestMapper.mapToModel(userRequestDto)));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(userResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userResponseMapper.mapToDto(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                            @RequestBody UserRequestDto userRequestDto) {
        User user = userService.getUserById(id);
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setAmountOfMoney(userRequestDto.getAmountOfMoney());
        User updatedUser = userService.createUser(user);
        return userResponseMapper.mapToDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public UserResponseDto deleteUserById(@PathVariable Long id) {
        User userToDelete = userService.getUserById(id);
        return userResponseMapper.mapToDto(userToDelete);
    }

    @PutMapping("/{id}/buy")
    public String buyProduct(@PathVariable Long id,
                                      @RequestParam Long productId) {
        userResponseMapper.mapToDto(userService.buyProduct(id, productId));
        return "Successful purchase!";
    }

    @GetMapping("/products/{id}")
    public List<UserResponseDto> getAllUsersByProductId(@PathVariable Long id) {
        List<UserResponseDto> users = userService.getAllUsersByProductId(id).stream()
                .map(e -> userResponseMapper.mapToDto(e))
                .collect(Collectors.toList());
        return users;
    }
}
