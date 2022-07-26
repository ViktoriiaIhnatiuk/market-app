package com.example.marketapp.service;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);

    UserResponseDto deleteUserById(Long id);

    UserResponseDto buyProduct(Long userId, Long productId);

    List<UserResponseDto> getAllUsersByProductId(Long id);
}
