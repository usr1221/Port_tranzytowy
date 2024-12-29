package com.port.transitPort.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "DOSTAWY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @Column(name = "NR_DOSTAWY")
    private Integer id;

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