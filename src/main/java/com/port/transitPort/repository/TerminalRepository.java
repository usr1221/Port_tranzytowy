package com.port.transitPort.repository;

import com.port.transitPort.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    // Custom query methods (if needed) can be added here
}
