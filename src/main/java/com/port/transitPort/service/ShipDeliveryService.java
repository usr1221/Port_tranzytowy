package com.port.transitPort.service;

import com.port.transitPort.model.ShipDelivery;
import com.port.transitPort.repository.ShipDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipDeliveryService {

    @Autowired
    private ShipDeliveryRepository shipDeliveryRepository;

    // Retrieve all ship deliveries
    public List<ShipDelivery> getAllShipDeliveries() {
        return shipDeliveryRepository.findAll();
    }

    // Retrieve a ship delivery by ID
    public ShipDelivery getShipDeliveryById(Long id) {
        return shipDeliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShipDelivery not found with ID: " + id));
    }

    // Create a new ship delivery
    public ShipDelivery createShipDelivery(ShipDelivery shipDelivery) {
        return shipDeliveryRepository.save(shipDelivery);
    }

    // Update an existing ship delivery
    public ShipDelivery updateShipDelivery(Long id, ShipDelivery updatedShipDelivery) {
        ShipDelivery shipDelivery = getShipDeliveryById(id); // Fetch existing ship delivery
        shipDelivery.setShipName(updatedShipDelivery.getShipName());
        shipDelivery.setHomePort(updatedShipDelivery.getHomePort());
        shipDelivery.setDraft(updatedShipDelivery.getDraft());
        shipDelivery.setLength(updatedShipDelivery.getLength());
        shipDelivery.setCallSign(updatedShipDelivery.getCallSign());
        shipDelivery.setShipType(updatedShipDelivery.getShipType());
        shipDelivery.setWharf(updatedShipDelivery.getWharf());
        return shipDeliveryRepository.save(shipDelivery);
    }

    // Delete a ship delivery by ID
    public void deleteShipDelivery(Long id) {
        if (!shipDeliveryRepository.existsById(id)) {
            throw new RuntimeException("ShipDelivery not found with ID: " + id);
        }
        shipDeliveryRepository.deleteById(id);
    }
}
