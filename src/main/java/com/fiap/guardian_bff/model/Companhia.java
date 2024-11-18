package com.fiap.guardian_bff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.guardian_bff.dto.companhia.AlterarCompanhiaDTO;
import com.fiap.guardian_bff.dto.companhia.CadastroCompanhiaDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
public class Companhia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private Long telefone;
    private String email;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Usuario> usuarios;

    public Companhia(CadastroCompanhiaDTO cadastroCompanhiaDTO) {
        this.razaoSocial = cadastroCompanhiaDTO.razaoSocial();
        this.cnpj = cadastroCompanhiaDTO.cnpj();
        this.endereco = cadastroCompanhiaDTO.endereco();
        this.telefone = cadastroCompanhiaDTO.telefone();
        this.email = cadastroCompanhiaDTO.email();
    }

    public void alterar(AlterarCompanhiaDTO alterarCompanhiaDTO) {
        if(alterarCompanhiaDTO.razaoSocial() != null) this.razaoSocial = alterarCompanhiaDTO.razaoSocial();
        if(alterarCompanhiaDTO.cnpj() != null) this.cnpj = alterarCompanhiaDTO.cnpj();
        if(alterarCompanhiaDTO.endereco() != null) this.endereco = alterarCompanhiaDTO.endereco();
        if(alterarCompanhiaDTO.telefone() != null) this.telefone = alterarCompanhiaDTO.telefone();
        if(alterarCompanhiaDTO.email() != null) this.email = alterarCompanhiaDTO.email();
    }
}
