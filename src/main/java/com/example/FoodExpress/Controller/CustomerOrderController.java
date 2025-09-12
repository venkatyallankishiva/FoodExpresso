package com.example.FoodExpress.Controller;


import com.example.FoodExpress.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

//    @PostMapping("/updateOrderCount")
//    public void updateOrderCount(@RequestBody OrderRequest orderRequest) {
//        customerOrderService.placeOrder(orderRequest.getOrderValue());
//    }

    @GetMapping("/getTotalOrders")
    public long getTotalOrders() {
        return customerOrderService.getTotalOrders();
    }
}

