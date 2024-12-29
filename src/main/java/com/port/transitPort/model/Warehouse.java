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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_magazyny")
    @SequenceGenerator(name = "seq_magazyny", sequenceName = "seq_magazyny", allocationSize = 1)
    @Column(name = "NR_MAGAZYNU")
    private Integer id;

    @Column(name = "POJEMNOŚĆ", nullable = false)
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
