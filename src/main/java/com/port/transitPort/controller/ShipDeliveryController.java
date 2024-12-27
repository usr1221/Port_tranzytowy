package com.port.transitPort.controller;

import com.port.transitPort.model.ShipDelivery;
import com.port.transitPort.service.ShipDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ship-deliveries")
public class ShipDeliveryController {

    @Autowired
    private ShipDeliveryService shipDeliveryService;

    // Get all ship deliveries
    @GetMapping
    public List<ShipDelivery> getAllShipDeliveries() {
        return shipDeliveryService.getAllShipDeliveries();
    }

    // Get a ship delivery by ID
    @GetMapping("/{id}")
    public ShipDelivery getShipDeliveryById(@PathVariable Long id) {
        return shipDeliveryService.getShipDeliveryById(id);
    }

    // Create a new ship delivery
    @PostMapping
    public ShipDelivery createShipDelivery(@RequestBody ShipDelivery shipDelivery) {
        return shipDeliveryService.createShipDelivery(shipDelivery);
    }

    // Update an existing ship delivery
    @PutMapping("/{id}")
    public ShipDelivery updateShipDelivery(@PathVariable Long id, @RequestBody ShipDelivery updatedShipDelivery) {
        return shipDeliveryService.updateShipDelivery(id, updatedShipDelivery);
    }

    // Delete a ship delivery by ID
    @DeleteMapping("/{id}")
    public void deleteShipDelivery(@PathVariable Long id) {
        shipDeliveryService.deleteShipDelivery(id);
    }
}
