package com.example.deliveryservice.repositories;

import com.example.deliveryservice.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
