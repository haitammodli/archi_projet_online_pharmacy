package com.example.deliveryservice.services;

import com.example.deliveryservice.dto.GeoResponse;
import com.example.deliveryservice.models.Delivery;
import com.example.deliveryservice.repositories.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final WebClient webClient;

    public Delivery createDelivery(Delivery delivery) {

        // Call external API for geolocation
        GeoResponse geo = webClient.get()
                .uri("https://api.geoapi.com/location?address=" + delivery.getAddress())
                .retrieve()
                .bodyToMono(GeoResponse.class)
                .timeout(Duration.ofSeconds(3))   // stop infinite looping
                .onErrorReturn(new GeoResponse(0, 0, "Unknown"))  // fallback
                .block();

        if (geo != null) {
            delivery.setLatitude(geo.getLat());
            delivery.setLongitude(geo.getLon());
            delivery.setEstimatedTime(geo.getEstimatedTime());
        }

        delivery.setStatus("PENDING");

        return deliveryRepository.save(delivery);
    }

    public Delivery getDelivery(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }
}
