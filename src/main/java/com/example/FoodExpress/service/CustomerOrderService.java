package com.example.FoodExpress.service;

import com.example.FoodExpress.entity.CustomerOrder;
import com.example.FoodExpress.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public void placeOrder(double orderValue) {
        CustomerOrder order = new CustomerOrder();
        order.setOrderTime(LocalDateTime.now());
        order.setOrderValue(orderValue);
        customerOrderRepository.save(order);
    }

    public long getTotalOrders() {
        return customerOrderRepository.count();
    }
}
