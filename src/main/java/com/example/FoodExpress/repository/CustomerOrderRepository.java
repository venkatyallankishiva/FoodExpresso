package com.example.FoodExpress.repository;

import com.example.FoodExpress.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    long countByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
}
