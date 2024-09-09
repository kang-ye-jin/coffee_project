package com.example.coffeeproject.repository;

import com.example.coffeeproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
