package com.fiap.guardian_bff.model;

import com.fiap.guardian_bff.dto.sensor.CadastroSensorDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table
@Entity
@Data
@NoArgsConstructor
public class SensorClima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSensorClima;
    private String timezone;
    private LocalDateTime dataHora;
    private double temperatura;
    private double umidade;
    private String direcaoVento;
    private double velocidadeVento;
    private String descricaoClima;
    private int idTorre;

    public SensorClima(CadastroSensorDTO cadastroSensorDTO) {
        this.timezone = cadastroSensorDTO.timezone();
        this.dataHora = cadastroSensorDTO.dataHora();
        this.temperatura = cadastroSensorDTO.temperatura();
        this.umidade = cadastroSensorDTO.umidade();
        this.direcaoVento = cadastroSensorDTO.direcaoVento();
        this.velocidadeVento = cadastroSensorDTO.velocidadeVento();
        this.descricaoClima = cadastroSensorDTO.descricaoClima();
        this.idTorre = cadastroSensorDTO.idTorre();
    }
}