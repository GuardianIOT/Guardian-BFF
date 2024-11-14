package com.fiap.guardian_bff.model;

import com.fiap.guardian_bff.dto.usuario.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, String senha) {
        this.nome = cadastroUsuarioDTO.nome();
        this.sobrenome = cadastroUsuarioDTO.sobrenome();
        this.email = cadastroUsuarioDTO.email();
        this.senha = senha;
    }
}
