package com.fiap.guardian_bff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Entity
@Data
@NoArgsConstructor
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataAlerta;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Companhia companhia;


    public Alerta(LocalDate dataAlerta, Companhia companhia) {
        this.dataAlerta = dataAlerta;
        this.companhia = companhia;
    }
}
