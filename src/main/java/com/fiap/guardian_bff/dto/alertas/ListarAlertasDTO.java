package com.fiap.guardian_bff.dto.alertas;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ListarAlertasDTO(
        Long id,
        LocalDate dataAlerta,
        Long idCompanhia
) {
}
