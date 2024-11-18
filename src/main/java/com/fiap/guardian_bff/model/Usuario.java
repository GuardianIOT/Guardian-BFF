package com.fiap.guardian_bff.model;

import com.fiap.guardian_bff.dto.usuario.AlterarUsuarioDTO;
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
    @ManyToOne
    private Companhia companhia;

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, String senha, Companhia companhia) {
        this.nome = cadastroUsuarioDTO.nome();
        this.sobrenome = cadastroUsuarioDTO.sobrenome();
        this.email = cadastroUsuarioDTO.email();
        this.senha = senha;
        this.companhia = companhia;
    }

    public void alterar(AlterarUsuarioDTO alterarUsuarioDTO) {
        if(alterarUsuarioDTO.nome() != null) this.nome = alterarUsuarioDTO.nome();
        if(alterarUsuarioDTO.sobrenome() != null) this.sobrenome = alterarUsuarioDTO.sobrenome();
        if(alterarUsuarioDTO.email() != null) this.email = alterarUsuarioDTO.email();
        if(alterarUsuarioDTO.senha() != null) this.senha = alterarUsuarioDTO.senha();

    }
}
