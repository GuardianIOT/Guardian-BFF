package com.fiap.guardian_bff.dto.sensor;

import java.time.LocalDateTime;

public record CadastroSensorDTO(
    String timezone,
    LocalDateTime dataHora,
    double temperatura,
    double umidade,
    String direcaoVento,
    double velocidadeVento,
    String descricaoClima,
    int idTorre
) {
}
