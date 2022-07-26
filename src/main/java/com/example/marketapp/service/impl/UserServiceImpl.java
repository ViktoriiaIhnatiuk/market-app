package com.example.marketapp.service.impl;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.mapper.RequestMapper;
import com.example.marketapp.mapper.ResponseMapper;
import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.ProductRepository;
import com.example.marketapp.repository.UserRepository;
import com.example.marketapp.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ResponseMapper<UserResponseDto, User> userResponseMapper;
    private final RequestMapper<UserRequestDto, User> userRequestMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           ResponseMapper<UserResponseDto, User> userResponseMapper,
                           RequestMapper<UserRequestDto, User> userRequestMapper) {
        this.userRepository = userRepository;

        this.productRepository = productRepository;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return userResponseMapper.mapToDto(userRepository.save(userRequestMapper.mapToModel(userRequestDto)));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userResponseMapper.mapToDto(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find user by id: " + id)));
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User userToUpdate = userRepository.getById(id);
        if (userRequestDto.getFirstName() != null) {
            userToUpdate.setFirstName(userRequestDto.getFirstName());
        }
        if (userRequestDto.getLastName() != null) {
            userToUpdate.setLastName(userRequestDto.getLastName());
        }
        if (userRequestDto.getAmountOfMoney() != null) {
            userToUpdate.setAmountOfMoney(userRequestDto.getAmountOfMoney());
        }
        return userResponseMapper.mapToDto(userRepository.save(userToUpdate));
    }

    @Override
    public UserResponseDto deleteUserById(Long id) {
        UserResponseDto userToDelete = userResponseMapper.mapToDto(
                userRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Can't delete user by id: " + id)));
        userRepository.deleteById(id);
        return userToDelete;
    }

    @Override
    public UserResponseDto buyProduct(Long userId, Long productId) {
        User user = userRepository.getById(userId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Can't find product by id: " + productId));
        if (product.getPrice().compareTo(user.getAmountOfMoney()) > 0) {
            throw new RuntimeException("Your amount of money is not enough");
        } else {
            user.setAmountOfMoney(user.getAmountOfMoney().subtract(product.getPrice()));
            Set<Product> products = user.getProducts();
            products.add(product);
            user.setProducts(products);
        }
        return userResponseMapper.mapToDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> getAllUsersByProductId(Long id) {
        List<UserResponseDto> users = userRepository.findAll().stream()
                .filter(e -> e.getProducts().contains(productRepository.getById(id)))
                .map(userResponseMapper::mapToDto)
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }
}
