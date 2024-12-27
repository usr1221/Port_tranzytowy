package com.port.transitPort.controller;

import com.port.transitPort.model.Port;
import com.port.transitPort.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ports")
public class PortController {

    @Autowired
    private PortService portService;

    @GetMapping
    public List<Port> getAllPorts() {
        return portService.getAllPorts();
    }

    @GetMapping("/{id}")
    public Port getPortById(@PathVariable Long id) {
        return portService.getPortById(id);
    }

    @PostMapping
    public Port createPort(@RequestBody Port port) {
        return portService.createPort(port);
    }

    @PutMapping("/{id}")
    public Port updatePort(@PathVariable Long id, @RequestBody Port port) {
        return portService.updatePort(id, port);
    }

    @DeleteMapping("/{id}")
    public void deletePort(@PathVariable Long id) {
        portService.deletePort(id);
    }
}
