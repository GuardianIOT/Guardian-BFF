package com.fiap.guardian_bff.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
            @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        Long idCompanhia
) {
}
