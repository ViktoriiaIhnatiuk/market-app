package com.example.marketapp.service;


import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;

import java.util.List;

public interface UserService {
   User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User deleteUserById(Long id);

    User buyProduct(Long userId, Long productId);

    List<User> getAllUsersByProductId(Long id);
}
