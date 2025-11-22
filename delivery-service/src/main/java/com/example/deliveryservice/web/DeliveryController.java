package com.example.deliveryservice.web;

import com.example.deliveryservice.models.Delivery;
import com.example.deliveryservice.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping("/delivery/{id}")
    public Delivery getDelivery(@PathVariable Long id) {
        return deliveryService.getDelivery(id);
    }
}
