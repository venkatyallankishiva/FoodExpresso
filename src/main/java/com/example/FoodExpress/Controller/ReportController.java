package com.example.FoodExpress.Controller;

import com.example.FoodExpress.entity.CustomerOrder;
import com.example.FoodExpress.entity.MenuItem;
import com.example.FoodExpress.repository.CustomerOrderRepository;
import com.example.FoodExpress.repository.CustomerRepository;
import com.example.FoodExpress.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CustomerOrderRepository orderRepository;

    @GetMapping("/report")
    public String viewReportPage(Model model) {
        return "report";
    }

    @PostMapping("/generateReport")
    public String generateReport(@RequestParam("menuReport") String menuReport,
                                 @RequestParam("orderReport") String orderReport,
                                 @RequestParam("customerActivity") String customerActivity,
                                 @RequestParam("menuPreferences") String menuPreferences,
                                 Model model) {
        long totalCustomers = customerRepository.count();

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime startOfNextDay = startOfDay.plusDays(1);

        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);

        LocalDate firstDayOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        LocalDate firstDayOfNextYear = firstDayOfYear.plusYears(1);

        long dailyOrders = countOrdersBetween(startOfDay, startOfNextDay);
        long monthlyOrders = countOrdersBetween(firstDayOfMonth.atStartOfDay(), firstDayOfNextMonth.atStartOfDay());
        long yearlyOrders = countOrdersBetween(firstDayOfYear.atStartOfDay(), firstDayOfNextYear.atStartOfDay());

        List<MenuItem> popularMenuItems = menuItemRepository.findAll();

        // Add data to the model
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("dailyOrders", dailyOrders);
        model.addAttribute("monthlyOrders", monthlyOrders);
        model.addAttribute("yearlyOrders", yearlyOrders);
        model.addAttribute("popularMenuItems", popularMenuItems);
        model.addAttribute("reportGenerated", true);

        return "report";
    }

    @PostMapping("/updateOrderCount")
    public void updateOrderCount(@RequestBody OrderRequest orderRequest) {
        CustomerOrder order = new CustomerOrder();
        order.setOrderTime(LocalDateTime.now());
        order.setOrderValue(orderRequest.getOrderValue());
        orderRepository.save(order);
    }

    private long countOrdersBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepository.countByOrderTimeBetween(start, end);
    }
}

class OrderRequest {
    private double orderValue;

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }
}
