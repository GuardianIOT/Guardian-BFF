package com.fiap.guardian_bff.dto.companhia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record CadastroCompanhiaDTO(
        @NotBlank
        String razaoSocial,
        @NotBlank
                @CNPJ
        String cnpj,
        @NotBlank
        String endereco,
        @NotNull
        Long telefone,
        @NotBlank
                @Email
        String email
) {
}
