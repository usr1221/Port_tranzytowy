package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "DOSTAWY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq_gen")
    @SequenceGenerator(name = "delivery_seq_gen", sequenceName = "SEQ_DOSTAWY", allocationSize = 1)
    @Column(name = "NR_DOSTAWY")
    private Long id;

    @Column(name = "STATUS", nullable = false, length = 12)
    private String status;

    @Column(name = "DATA_WPLYNIECIA")
    private Date arrivalDate;

    @Column(name = "DATA_REALIZACJI")
    private Date completionDate;

    @Column(name = "DATA_WYPLYNIECA")
    private Date departureDate;

    @Column(name = "ILOSC_TOWARU", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "NR_STATKU", nullable = false)
    private Ship ship;
}