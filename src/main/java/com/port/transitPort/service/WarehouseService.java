package com.port.transitPort.service;

import com.port.transitPort.model.Warehouse;
import com.port.transitPort.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    // Retrieve all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Retrieve a warehouse by ID
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + id));
    }

    // Create a new warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // Update an existing warehouse
    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        Warehouse warehouse = getWarehouseById(id); // Fetch existing warehouse
        warehouse.setCapacity(updatedWarehouse.getCapacity());
        warehouse.setOccupancy(updatedWarehouse.getOccupancy());
        warehouse.setTerminal(updatedWarehouse.getTerminal());
        return warehouseRepository.save(warehouse);
    }

    // Delete a warehouse by ID
    public void deleteWarehouse(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new RuntimeException("Warehouse not found with ID: " + id);
        }
        warehouseRepository.deleteById(id);
    }
}
