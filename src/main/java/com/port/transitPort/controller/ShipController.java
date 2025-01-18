package com.port.transitPort.controller;

import com.port.transitPort.model.Ship;
import com.port.transitPort.service.ShipService;
import com.port.transitPort.service.WharfService;
import com.port.transitPort.util.requests.ShipRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private WharfService wharfService;

    @Autowired
    private ShipService shipService;

    // Get all ships
    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    // Get a specific ship by ID
    @GetMapping("/{id}")
    public Ship getShipById(@PathVariable Long id) {
        return shipService.getShipById(id);
    }

    // Create a new ship
    @PostMapping
    public Ship createShip(@RequestBody ShipRequest shipRequest) {
        Ship ship = Ship.builder().name(shipRequest.getName())
                .homePort(shipRequest.getHomePort())
                .draft(shipRequest.getDraft())
                .length(shipRequest.getLength())
                .callSign(shipRequest.getCallSign())
                .type(shipRequest.getType())
                .build();
        if (shipRequest.getWharf() != null){
            ship.setWharf(wharfService.getWharfById(shipRequest.getWharf()));
        }
        return shipService.createShip(ship);
    }

    // Update an existing ship
    @PutMapping("/{id}")
    public Ship updateShip(@PathVariable Long id, @RequestBody ShipRequest shipRequest) {
        Ship ship = Ship.builder().name(shipRequest.getName())
                .homePort(shipRequest.getHomePort())
                .draft(shipRequest.getDraft())
                .length(shipRequest.getLength())
                .callSign(shipRequest.getCallSign())
                .type(shipRequest.getType())
                .build();
        return shipService.updateShip(id, ship);
    }

    // Delete a ship by ID
    @DeleteMapping("/{id}")
    public void deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
    }
}
