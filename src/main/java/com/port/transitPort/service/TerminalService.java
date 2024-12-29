package com.port.transitPort.service;

import com.port.transitPort.model.Terminal;
import com.port.transitPort.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    // Retrieve all terminals
    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }

    // Retrieve a terminal by ID
    public Terminal getTerminalById(Long id) {
        return terminalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terminal not found with ID: " + id));
    }

    // Create a new terminal
    public Terminal createTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    // Update an existing terminal
    public Terminal updateTerminal(Long id, Terminal updatedTerminal) {
        Terminal terminal = getTerminalById(id); // Fetch existing terminal
        terminal.setName(updatedTerminal.getName());
        terminal.setWharvesCount(updatedTerminal.getWharvesCount());
        terminal.setPort(updatedTerminal.getPort());
        return terminalRepository.save(terminal);
    }

    // Delete a terminal by ID
    public void deleteTerminal(Long id) {
        if (!terminalRepository.existsById(id)) {
            throw new RuntimeException("Terminal not found with ID: " + id);
        }
        terminalRepository.deleteById(id);
    }
}
