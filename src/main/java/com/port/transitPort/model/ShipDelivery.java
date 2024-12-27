package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "STATEK_DOSTAWA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOSTAWY")
    private Long id;

    @Column(name = "NAZWA_STATKU", nullable = false, length = 30)
    private String shipName;

    @Column(name = "PORT_MACIERZYSTY", nullable = false, length = 40)
    private String homePort;

    @Column(name = "ZANURZENIE", nullable = false)
    private Integer draft;

    @Column(name = "DLUGOSC", nullable = false)
    private Integer length;

    @Column(name = "ZNAK_WYWOLAWCZY", nullable = false, length = 10)
    private String callSign;

    @Column(name = "TYP_STATKU", nullable = false, length = 13)
    private String shipType;

    @ManyToOne
    @JoinColumn(name = "ID_NABRZEZA", nullable = true)
    private Wharf wharf;

    public void assignWharf(Wharf wharf) {
        this.wharf = wharf;
        if (wharf != null && !wharf.getShipDeliveries().contains(this)) {
            wharf.getShipDeliveries().add(this);
        }
    }
}
