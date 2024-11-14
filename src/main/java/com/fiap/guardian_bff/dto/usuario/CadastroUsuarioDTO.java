package com.fiap.guardian_bff.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroUsuarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
            @Email
        String email,
        @NotBlank
        String senha
) {
}
