package com.port.transitPort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PORTY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Port {

    @Id
    @Column(name = "NR_PORTU")
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Terminal> terminals = new ArrayList<>();

    public void addTerminal(Terminal terminal) {
        terminals.add(terminal);
        terminal.setPort(this);
    }

    public void removeTerminal(Terminal terminal) {
        terminals.remove(terminal);
        terminal.setPort(null);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setPort(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setPort(null);
    }
}
