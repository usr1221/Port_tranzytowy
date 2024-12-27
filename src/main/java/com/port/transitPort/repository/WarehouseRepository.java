package com.port.transitPort.repository;

import com.port.transitPort.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // Custom query methods (if needed) can be added here
}
