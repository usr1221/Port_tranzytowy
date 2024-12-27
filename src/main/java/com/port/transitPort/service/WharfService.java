package com.port.transitPort.service;

import com.port.transitPort.model.Wharf;
import com.port.transitPort.repository.WharfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WharfService {

    @Autowired
    private WharfRepository wharfRepository;

    // Retrieve all wharves
    public List<Wharf> getAllWharves() {
        return wharfRepository.findAll();
    }

    // Retrieve a wharf by ID
    public Wharf getWharfById(Long id) {
        return wharfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wharf not found with ID: " + id));
    }

    // Create a new wharf
    public Wharf createWharf(Wharf wharf) {
        return wharfRepository.save(wharf);
    }

    // Update an existing wharf
    public Wharf updateWharf(Long id, Wharf updatedWharf) {
        Wharf wharf = getWharfById(id); // Fetch existing wharf
        wharf.setLength(updatedWharf.getLength());
        wharf.setDepth(updatedWharf.getDepth());
        wharf.setTerminal(updatedWharf.getTerminal());
        return wharfRepository.save(wharf);
    }

    // Delete a wharf by ID
    public void deleteWharf(Long id) {
        if (!wharfRepository.existsById(id)) {
            throw new RuntimeException("Wharf not found with ID: " + id);
        }
        wharfRepository.deleteById(id);
    }
}
