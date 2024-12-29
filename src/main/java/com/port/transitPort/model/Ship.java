package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "STATKI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_statki")
    @SequenceGenerator(name = "seq_statki", sequenceName = "seq_statki", allocationSize = 1)
    @Column(name = "NR_STATKU")
    private Integer id;

    @Column(name = "NAZWA_STATKU", nullable = false, length = 30)
    private String name;

    @Column(name = "PORT_MACIERZYSTY", nullable = false, length = 40)
    private String homePort;

    @Column(name = "ZANURZENIE", nullable = false)
    private Integer draft;

    @Column(name = "DLUGOSC", nullable = false)
    private Integer length;

    @Column(name = "SYGNAL_WYWOLAWCZY", nullable = false, length = 10)
    private String callSign;

    @Column(name = "TYP_STATKU", nullable = false, length = 13)
    private String type;

    @ManyToOne
    @JoinColumn(name = "NR_NABRZEZA", nullable = true)
    private Wharf wharf;

    // Przypisuje statek do nabrzeża
    public void assignToWharf(Wharf wharf) {
        this.wharf = wharf;
        if (wharf != null && !wharf.getShips().contains(this)) {
            wharf.getShips().add(this);
        }
    }

    // Usuwa statek z nabrzeża
    public void unassignFromWharf() {
        if (this.wharf != null) {
            this.wharf.getShips().remove(this);
            this.wharf = null;
        }
    }
}