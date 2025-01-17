package com.port.transitPort.controller;

import com.port.transitPort.model.Terminal;
import com.port.transitPort.service.PortService;
import com.port.transitPort.service.TerminalService;
import com.port.transitPort.service.WarehouseService;
import com.port.transitPort.service.WharfService;
import com.port.transitPort.util.requests.TerminalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terminals")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private PortService portService;
    @Autowired
    private WharfService wharfService;
    @Autowired
    private WarehouseService warehouseService;

    // Get all terminals
    @GetMapping
    public List<Terminal> getAllTerminals() {
        return terminalService.getAllTerminals();
    }

    // Get a terminal by ID
    @GetMapping("/{id}")
    public Terminal getTerminalById(@PathVariable Long id) {
        return terminalService.getTerminalById(id);
    }

    // Create a new terminal
    @PostMapping
    public Terminal createTerminal(@RequestBody TerminalRequest terminalRequest) {
        Terminal terminal = Terminal.builder()
                .name(terminalRequest.getName())
                .wharvesCount(terminalRequest.getWharvesCount())
                .port(portService.getPortById(terminalRequest.getPortId()))
                .build();
        for (Long wharfId : terminalRequest.getWharfIds()){
            terminal.addWharf(wharfService.getWharfById(wharfId));
        }
        for (Long warehouseId : terminalRequest.getWarehouseIds()){
            terminal.addWarehouse(warehouseService.getWarehouseById(warehouseId));
        }
        return terminalService.createTerminal(terminal);
    }

    // Update an existing terminal
    @PutMapping("/{id}")
    public Terminal updateTerminal(@PathVariable Long id, @RequestBody Terminal updatedTerminal) {
        return terminalService.updateTerminal(id, updatedTerminal);
    }

    // Delete a terminal by ID
    @DeleteMapping("/{id}")
    public void deleteTerminal(@PathVariable Long id) {
        terminalService.deleteTerminal(id);
    }
}
