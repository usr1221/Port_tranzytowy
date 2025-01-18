package com.port.transitPort.controller;

import com.port.transitPort.model.Wharf;
import com.port.transitPort.service.TerminalService;
import com.port.transitPort.service.WharfService;
import com.port.transitPort.util.requests.WharfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wharves")
public class WharfController {

    @Autowired
    private WharfService wharfService;

    @Autowired
    private TerminalService terminalService;

    // Get all wharves
    @GetMapping
    public List<Wharf> getAllWharves() {
        return wharfService.getAllWharves();
    }

    // Get a wharf by ID
    @GetMapping("/{id}")
    public Wharf getWharfById(@PathVariable Long id) {
        return wharfService.getWharfById(id);
    }

    // Create a new wharf
    @PostMapping
    public Wharf createWharf(@RequestBody WharfRequest wharfRequest) {
        Wharf wharf = Wharf.builder()
                .depth(wharfRequest.getDepth())
                .length(wharfRequest.getDepth())
                .terminal(terminalService.getTerminalById(wharfRequest.getTerminalId()))
                .build();
        return wharfService.createWharf(wharf);
    }

    // Update an existing wharf
    @PutMapping("/{id}")
    public Wharf updateWharf(@PathVariable Long id, @RequestBody WharfRequest wharfRequest) {
        Wharf wharf = Wharf.builder()
                .depth(wharfRequest.getDepth())
                .length(wharfRequest.getDepth())
                .terminal(terminalService.getTerminalById(wharfRequest.getTerminalId()))
                .build();
        return wharfService.updateWharf(id, wharf);
    }

    // Delete a wharf by ID
    @DeleteMapping("/{id}")
    public void deleteWharf(@PathVariable Long id) {
        wharfService.deleteWharf(id);
    }
}
