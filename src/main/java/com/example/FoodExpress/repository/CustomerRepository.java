package com.example.FoodExpress.repository;

import com.example.FoodExpress.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
