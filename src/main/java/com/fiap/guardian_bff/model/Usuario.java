package com.fiap.guardian_bff.model;

import com.fiap.guardian_bff.dto.usuario.AlterarUsuarioDTO;
import com.fiap.guardian_bff.dto.usuario.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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
    @ToString.Exclude
    private Companhia companhia;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role")
    private Set<String> roles;

    public Usuario(String nome, String sobrenome, String email, String senha, Companhia companhia, Set<String> roles) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.companhia = companhia;
        this.roles = roles;
    }

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, String senha, Companhia companhia) {
        this.nome = cadastroUsuarioDTO.nome();
        this.sobrenome = cadastroUsuarioDTO.sobrenome();
        this.email = cadastroUsuarioDTO.email();
        this.senha = senha;
        this.companhia = companhia;
        this.roles = new HashSet<>();
        this.roles.add("ROLE_USER");
    }

    public void alterar(AlterarUsuarioDTO alterarUsuarioDTO) {
        if(alterarUsuarioDTO.nome() != null) this.nome = alterarUsuarioDTO.nome();
        if(alterarUsuarioDTO.sobrenome() != null) this.sobrenome = alterarUsuarioDTO.sobrenome();
        if(alterarUsuarioDTO.email() != null) this.email = alterarUsuarioDTO.email();
        if(alterarUsuarioDTO.senha() != null) this.senha = alterarUsuarioDTO.senha();

    }
}
