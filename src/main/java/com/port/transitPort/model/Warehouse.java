package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MAGAZYN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAGAZYNU")
    private Long id;

    @Column(name = "POJEMNOŚĆ", nullable = false)
    private Integer capacity;

    @Column(name = "ZAPEŁNIENIE", nullable = false)
    private Integer occupancy;

    @ManyToOne
    @JoinColumn(name = "ID_TERMINALU", nullable = false)
    private Terminal terminal;

    public void assignTerminal(Terminal terminal) {
        this.terminal = terminal;
        if (terminal != null && !terminal.getWarehouses().contains(this)) {
            terminal.getWarehouses().add(this);
        }
    }
}
