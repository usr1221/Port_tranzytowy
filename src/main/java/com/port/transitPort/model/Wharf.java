package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NABRZEŻE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wharf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NABRZEZA")
    private Long id;

    @Column(name = "DLUGOSC", nullable = false)
    private Integer length;

    @Column(name = "GLEBOKOSC", nullable = false)
    private Integer depth;

    @ManyToOne
    @JoinColumn(name = "ID_TERMINALU", nullable = false)
    private Terminal terminal;

    @OneToMany(mappedBy = "wharf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipDelivery> shipDeliveries = new ArrayList<>();

    public void addShipDelivery(ShipDelivery shipDelivery) {
        shipDeliveries.add(shipDelivery);
        shipDelivery.setWharf(this);
    }

    public void removeShipDelivery(ShipDelivery shipDelivery) {
        shipDeliveries.remove(shipDelivery);
        shipDelivery.setWharf(null);
    }

    public void assignTerminal(Terminal terminal) {
        this.terminal = terminal;
        if (terminal != null && !terminal.getWharves().contains(this)) {
            terminal.getWharves().add(this);
        }
    }
}
