package com.fiap.guardian_bff.dto.companhia;

public record AlterarCompanhiaDTO(
        String razaoSocial,
        String cnpj,
        String endereco,
        Long telefone,
        String email
) {
}
