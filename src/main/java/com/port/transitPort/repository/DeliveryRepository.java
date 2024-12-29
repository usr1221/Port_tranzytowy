package com.port.transitPort.repository;

import com.port.transitPort.model.Delivery;
import com.port.transitPort.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // Custom query methods (if needed) can be added here
}
