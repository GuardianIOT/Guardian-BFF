package com.fiap.guardian_bff.dto.usuario;

public record AlterarUsuarioDTO(
        String nome,
        String sobrenome,
        String email,
        String senha
) {
}
