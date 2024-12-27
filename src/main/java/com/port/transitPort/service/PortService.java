package com.port.transitPort.service;

import com.port.transitPort.model.Port;
import com.port.transitPort.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {

    @Autowired
    private PortRepository portRepository;

    // Retrieve all ports
    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    // Retrieve a specific port by ID
    public Port getPortById(Long id) {
        return portRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Port not found with ID: " + id));
    }

    // Create a new port
    public Port createPort(Port port) {
        return portRepository.save(port);
    }

    // Update an existing port
    public Port updatePort(Long id, Port updatedPort) {
        Port port = getPortById(id); // Fetch existing port
        port.setName(updatedPort.getName());
        port.setAddress(updatedPort.getAddress());
        return portRepository.save(port);
    }

    // Delete a port by ID
    public void deletePort(Long id) {
        if (!portRepository.existsById(id)) {
            throw new RuntimeException("Port not found with ID: " + id);
        }
        portRepository.deleteById(id);
    }
}
