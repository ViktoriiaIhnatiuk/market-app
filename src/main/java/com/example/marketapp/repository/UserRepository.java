package com.example.marketapp.repository;

import com.example.marketapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT*\n"
            + "FROM users_products as up\n"
            + "LEFT JOIN users as u\n"
            + "ON up.user_id = u.id\n"
            + "WHERE products_id = ?", nativeQuery = true)
    List<User> getAllUsersByProductId(Long productId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"products"})
    List<User> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"products"})
    Optional<User> findAllById(Long id);
}
