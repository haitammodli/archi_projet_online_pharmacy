package com.example.orderservice.services;


import com.example.orderservice.clients.ProductRestClient;
import com.example.orderservice.models.Order;
import com.example.orderservice.models.OrderItem;
import com.example.orderservice.dto.Product;
import com.example.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRestClient productClient;

    public Order createOrder(Order order) {
        double total = 0;

        for (OrderItem item : order.getItems()) {
            Product p = productClient.getProductById(item.getProductId());

            item.setProductName(p.getName());
            item.setPrice(p.getPrice());
            item.setLineTotal(p.getPrice() * item.getQuantity());
            item.setOrder(order);

            total += item.getLineTotal();
        }

        order.setTotalPrice(total);
        order.setStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public java.util.List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
