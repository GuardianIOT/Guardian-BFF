package com.fiap.guardian_bff.dto.alertas;

import java.time.LocalDate;

public record AlertaDTO(
        LocalDate dataAlerta,
        Long idCompanhia
) {
}
