package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PORT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PORTU")
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 30)
    private String name;

    @Column(name = "ADRES", nullable = false, length = 50)
    private String address;

    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Terminal> terminals = new ArrayList<>();

    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();

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
