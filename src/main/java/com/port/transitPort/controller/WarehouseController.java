package com.port.transitPort.controller;

import com.port.transitPort.model.Warehouse;
import com.port.transitPort.service.TerminalService;
import com.port.transitPort.service.WarehouseService;
import com.port.transitPort.util.requests.WarehouseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private TerminalService terminalService;

    // Get all warehouses
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // Get a warehouse by ID
    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    // Create a new warehouse
    @PostMapping
    public Warehouse createWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        Warehouse warehouse = Warehouse.builder()
                .capacity(warehouseRequest.getCapacity())
                .occupancy(warehouseRequest.getOccupancy())
                .terminal(terminalService.getTerminalById(warehouseRequest.getTerminalId()))
                .build();
        return warehouseService.createWarehouse(warehouse);
    }

    // Update an existing warehouse
    @PutMapping("/{id}")
    public Warehouse updateWarehouse(@PathVariable Long id, @RequestBody Warehouse updatedWarehouse) {
        return warehouseService.updateWarehouse(id, updatedWarehouse);
    }

    // Delete a warehouse by ID
    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }
}
