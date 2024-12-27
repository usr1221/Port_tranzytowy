package com.port.transitPort.controller;

import com.port.transitPort.model.Terminal;
import com.port.transitPort.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terminals")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

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
    public Terminal createTerminal(@RequestBody Terminal terminal) {
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
