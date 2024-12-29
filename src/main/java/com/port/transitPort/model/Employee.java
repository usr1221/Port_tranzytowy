package com.port.transitPort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "PRACOWNICY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pracownicy")
    @SequenceGenerator(name = "seq_pracownicy", sequenceName = "seq_pracownicy", allocationSize = 1)
    @Column(name = "NR_PRACOWNIKA")
    private Integer id;

    @Column(name = "STANOWISKO", nullable = false, length = 14)
    private String position;

    @Column(name = "IMIE", nullable = false, length = 30)
    private String firstName;

    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String lastName;

    @Column(name = "NARODOWOSC", nullable = false, length = 2)
    private String nationality;

    @Column(name = "PESEL", length = 11)
    private String pesel;

    @Column(name = "NUMER_TELEFONU", length = 14)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false, length = 40)
    private String email;

    @Column(name = "HASH_HASLA", nullable = false, length = 64)
    private String passwordHash;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "NR_PORTU", nullable = false)
    private Port port;

    public String getRole() {
        switch (this.position) {
            case "Administrator":
                return "ROLE_ADMIN";
            case "Konserwator":
                return "ROLE_MAINTAINER";
            case "Magazynier":
                return "ROLE_WAREHOUSE";
            case "Przeladunkowy":
                return "ROLE_HANDLER";
            default:
                return "ROLE_USER";
        }
    }

}
