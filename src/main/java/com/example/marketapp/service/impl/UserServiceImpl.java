package com.example.marketapp.service.impl;

import com.example.marketapp.model.Product;
import com.example.marketapp.model.User;
import com.example.marketapp.repository.UserRepository;
import com.example.marketapp.service.ProductService;
import com.example.marketapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductService productService;

    public UserServiceImpl(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;

        this.productService = productService;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find user by id: " + id));
    }

    @Override
    public User deleteUserById(Long id) {
        User userToDelete = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete user by id: " + id));
        return userToDelete;
    }

    @Override
    public User buyProduct(Long userId, Long productId) {
        User user = getUserById(userId);
        User updatedUser = new User();
        Product product = productService.getProductById(productId);
        if (product.getPrice().compareTo(user.getAmountOfMoney()) > 0) {
            throw new RuntimeException("Your amount of money is not enough");
        } else {
            user.setAmountOfMoney(user.getAmountOfMoney().subtract(product.getPrice()));
            List<Product> products = user.getProducts();
            products.add(product);
            user.setProducts(products);
            updatedUser = createUser(user);
        }
        return updatedUser;
    }

    @Override
    public List<User> getAllUsersByProductId(Long id) {
        List<User> users = userRepository.findAll().stream()
                .filter(e -> e.getProducts().contains(productService.getProductById(id)))
                .collect(Collectors.toList());
        return users;
    }


}