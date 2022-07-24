package com.example.marketapp.mapper;

import com.example.marketapp.dto.request.UserRequestDto;
import com.example.marketapp.dto.response.UserResponseDto;
import com.example.marketapp.model.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestMapper<UserRequestDto, User>,
        ResponseMapper<UserResponseDto, User> {
    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAmountOfMoney(dto.getAmountOfMoney());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setAmountOfMoney(user.getAmountOfMoney());
        if (user.getProducts() != null) {
            userResponseDto.setProductsIds(user.getProducts().stream().map(e -> e.getId())
                    .collect(Collectors.toList()));
        }
        return userResponseDto;
    }
}
