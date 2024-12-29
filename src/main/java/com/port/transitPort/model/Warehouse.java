package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MAGAZYNY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @Column(name = "NR_MAGAZYNU")
    private Integer id;

    @Column(name = "POJEMNOSC", nullable = false)
    private Integer capacity;

    @Column(name = "ZAPELNIENIE", nullable = false)
    private Integer occupancy;

    @ManyToOne
    @JoinColumn(name = "NR_TERMINALA", nullable = false)
    private Terminal terminal;

    public void assignTerminal(Terminal terminal) {
        this.terminal = terminal;
        if (terminal != null && !terminal.getWarehouses().contains(this)) {
            terminal.getWarehouses().add(this);
        }
    }
}
