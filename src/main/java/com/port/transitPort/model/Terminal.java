package com.port.transitPort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
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
@Builder
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "terminal_seq_gen")
    @SequenceGenerator(name = "terminal_seq_gen", sequenceName = "SEQ_TERMINALE", allocationSize = 1)
    @Column(name = "NR_TERMINALA")
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 20)
    private String name;

    @Column(name = "ILOSC_NABRZEZY", nullable = false)
    private Integer wharvesCount;

    @ManyToOne
    @JoinColumn(name = "NR_PORTU", nullable = false)
    private Port port;

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Wharf> wharves = new ArrayList<>();

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
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
