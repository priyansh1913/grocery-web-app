package com.example.groceryapp.repository;

import com.example.groceryapp.model.Order;
import com.example.groceryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByCreatedAtDesc(User user);
}
