package com.fiap.guardian_bff.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Parque {
    private String nome;
    private LocalDateTime anoInauguracao;
    private int localizacaoId;
    private double areaKm;
    private String tecnologia;
    private String statusOperacao;
    private int numeroAerogeradores;
    private List<Integer> aerogeradorIds;
    private Localizacao localizacao;
}

