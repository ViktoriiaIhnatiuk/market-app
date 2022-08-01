package com.example.marketapp.repository;

import com.example.marketapp.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT*\n"
            + "FROM users_products as up\n"
            + "LEFT JOIN products as p\n"
            + "ON up.products_id = p.id\n"
            + "WHERE user_id = 1;", nativeQuery = true)
    List<Product> getAllProductsByUserId(Long id);
}
