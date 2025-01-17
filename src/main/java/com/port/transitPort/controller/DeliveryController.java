package com.port.transitPort.controller;

import com.port.transitPort.model.Delivery;
import com.port.transitPort.service.DeliveryService;
import com.port.transitPort.util.requests.DeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private ShipController shipService;
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping
    public Delivery createDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        Delivery delivery = Delivery.builder()
                .status(deliveryRequest.getStatus())
                .arrivalDate(deliveryRequest.getArrivalDate())
                .completionDate(deliveryRequest.getCompletionDate())
                .departureDate(deliveryRequest.getDepartureDate())
                .quantity(deliveryRequest.getQuantity())
                .ship(shipService.getShipById(deliveryRequest.getShipId()))
                .build();
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @RequestBody DeliveryRequest deliveryRequest) {
        Delivery delivery = Delivery.builder()
                .status(deliveryRequest.getStatus())
                .arrivalDate(deliveryRequest.getArrivalDate())
                .completionDate(deliveryRequest.getCompletionDate())
                .departureDate(deliveryRequest.getDepartureDate())
                .quantity(deliveryRequest.getQuantity())
                .ship(shipService.getShipById(deliveryRequest.getShipId()))
                .build();
        return deliveryService.updateDelivery(id, delivery);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}
