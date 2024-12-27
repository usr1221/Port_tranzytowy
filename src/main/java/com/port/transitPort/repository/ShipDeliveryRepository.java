package com.port.transitPort.repository;

import com.port.transitPort.model.ShipDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipDeliveryRepository extends JpaRepository<ShipDelivery, Long> {
    // Custom query methods (if needed) can be added here
}
