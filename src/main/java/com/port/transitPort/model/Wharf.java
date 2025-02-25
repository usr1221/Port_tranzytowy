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
@Table(name = "NABRZEZA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wharf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wharves_seq_gen")
    @SequenceGenerator(name = "wharves_seq_gen", sequenceName = "SEQ_NABRZEZA", allocationSize = 1)
    @Column(name = "NR_NABRZEZA")
    private Long id;

    @Column(name = "DLUGOSC", nullable = false)
    private Integer length;

    @Column(name = "GLEBOKOSC", nullable = false)
    private Integer depth;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "NR_TERMINALA", nullable = false)
    private Terminal terminal;

    @OneToMany(mappedBy = "wharf", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ship> ships = new ArrayList<>();

    // Dodaje statek do listy powiązanej z nabrzeżem
    public void addShip(Ship ship) {
        if (!ships.contains(ship)) {
            ships.add(ship);
            ship.setWharf(this); // Ustawia relację w obie strony
        }
    }

    // Usuwa statek z listy powiązanej z nabrzeżem
    public void removeShip(Ship ship) {
        if (ships.contains(ship)) {
            ships.remove(ship);
            ship.setWharf(null); // Zrywa relację w obie strony
        }
    }

    // Przypisuje nabrzeże do terminala
    public void assignToTerminal(Terminal terminal) {
        if (this.terminal != null) {
            unassignFromTerminal(); // Zrywa istniejącą relację z innym terminalem
        }
        this.terminal = terminal;
        if (terminal != null && !terminal.getWharves().contains(this)) {
            terminal.getWharves().add(this);
        }
    }

    // Usuwa nabrzeże z terminala
    public void unassignFromTerminal() {
        if (this.terminal != null) {
            this.terminal.getWharves().remove(this);
            this.terminal = null;
        }
    }
}
