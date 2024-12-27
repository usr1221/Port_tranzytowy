package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TERMINAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TERMINALU")
    private Long id;

    @Column(name = "NAZWA_TERMINALU", nullable = false, length = 20)
    private String name;

    @Column(name = "LICZBA_NABRZEŻY", nullable = false)
    private Integer numberOfWharves;

    @ManyToOne
    @JoinColumn(name = "ID_PORTU", nullable = false)
    private Port port;

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wharf> wharves = new ArrayList<>();

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Warehouse> warehouses = new ArrayList<>();

    public void addWharf(Wharf wharf) {
        wharves.add(wharf);
        wharf.setTerminal(this);
    }

    public void removeWharf(Wharf wharf) {
        wharves.remove(wharf);
        wharf.setTerminal(null);
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        warehouse.setTerminal(this);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
        warehouse.setTerminal(null);
    }
}
