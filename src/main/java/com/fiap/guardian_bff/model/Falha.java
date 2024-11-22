package com.fiap.guardian_bff.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Falha {
    private LocalDateTime dataHora;
    private String descricao;
    private String status;
    private String prioridade;
    private String tipo;
    private String equipeManutencaoResponsavel;
    private LocalDateTime dataResolucao;
    private List<Integer> aerogeradorIds;
}

