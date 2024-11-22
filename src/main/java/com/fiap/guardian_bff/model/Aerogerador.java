package com.fiap.guardian_bff.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Aerogerador {
    private String modelo;
    private String tecnologia;
    private double capacidadeMW;
    private double alturaMastro;
    private double velocidadeCorte;
    private String statusOperacao;
    private double diametroMotor;
    private LocalDate dataInstalacao;
    private LocalDate garantia;
    private int parqueId;
}