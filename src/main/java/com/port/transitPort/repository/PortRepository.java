package com.port.transitPort.repository;

import com.port.transitPort.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
    // Additional custom methods can be added here if needed
}
