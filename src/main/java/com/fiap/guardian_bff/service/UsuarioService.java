package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.config.SecurityConfig;
import com.fiap.guardian_bff.dto.usuario.CadastroUsuarioDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public Usuario cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) throws BusinessException {
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(cadastroUsuarioDTO.email());

        if(usuarioByEmail.isPresent()){
            throw new BusinessException("O e-mail informado já está em uso. Por favor, utilize um e-mail diferente.");
        }

        String senhaCriptografada = securityConfig.passwordEncoder().encode(cadastroUsuarioDTO.senha());
        Usuario usuario = new Usuario(cadastroUsuarioDTO, senhaCriptografada);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
