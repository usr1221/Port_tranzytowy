package com.port.transitPort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MAGAZYNY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_seq_gen")
    @SequenceGenerator(name = "warehouse_seq_gen", sequenceName = "SEQ_MAGAZYNY", allocationSize = 1)
    @Column(name = "NR_MAGAZYNU")
    private Long id;

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
