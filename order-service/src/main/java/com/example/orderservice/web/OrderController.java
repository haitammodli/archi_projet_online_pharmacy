package com.example.orderservice.web;

import com.example.orderservice.models.Order;
import com.example.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/orders")
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOne(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

}
