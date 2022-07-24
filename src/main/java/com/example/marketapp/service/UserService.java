package com.example.marketapp.service;

import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.model.User;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(User user);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUserById(Long id, User user);

    UserResponseDto deleteUserById(Long id);

    UserResponseDto buyProduct(Long userId, Long productId);

    List<UserResponseDto> getAllUsersByProductId(Long id);
}
