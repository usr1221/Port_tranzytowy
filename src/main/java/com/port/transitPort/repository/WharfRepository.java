package com.port.transitPort.repository;

import com.port.transitPort.model.Wharf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WharfRepository extends JpaRepository<Wharf, Long> {
    // Custom query methods (if needed) can be added here
}
