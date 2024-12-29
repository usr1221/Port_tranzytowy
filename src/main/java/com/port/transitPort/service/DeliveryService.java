package com.port.transitPort.service;

import com.port.transitPort.model.Delivery;
import com.port.transitPort.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    // Retrieve all deliveries
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    // Retrieve a delivery by ID
    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with ID: " + id));
    }

    // Create a new delivery
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // Update an existing delivery
    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        Delivery delivery = getDeliveryById(id); // Fetch existing delivery
        delivery.setStatus(updatedDelivery.getStatus());
        delivery.setArrivalDate(updatedDelivery.getArrivalDate());
        delivery.setCompletionDate(updatedDelivery.getCompletionDate());
        delivery.setDepartureDate(updatedDelivery.getDepartureDate());
        delivery.setQuantity(updatedDelivery.getQuantity());
        delivery.setShip(updatedDelivery.getShip());
        return deliveryRepository.save(delivery);
    }

    // Delete a delivery by ID
    public void deleteDelivery(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new RuntimeException("Delivery not found with ID: " + id);
        }
        deliveryRepository.deleteById(id);
    }
}
