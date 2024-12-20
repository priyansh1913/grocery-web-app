package com.example.groceryapp.repository;

import com.example.groceryapp.model.Cart;
import com.example.groceryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
