package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TERMINALE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_terminale")
    @SequenceGenerator(name = "seq_terminale", sequenceName = "seq_terminale", allocationSize = 1)
    @Column(name = "NR_TERMINALA")
    private Integer id;

    @Column(name = "NAZWA", nullable = false, length = 20)
    private String name;

    @Column(name = "ILOSC_NABRZEZY", nullable = false)
    private Integer wharvesCount;

    @ManyToOne
    @JoinColumn(name = "NR_PORTU", nullable = false)
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
