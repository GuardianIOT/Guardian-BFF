package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.config.SecurityConfig;
import com.fiap.guardian_bff.dto.usuario.AlterarUsuarioDTO;
import com.fiap.guardian_bff.dto.usuario.CadastroUsuarioDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.repository.CompanhiaRepository;
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

    @Autowired
    private CompanhiaRepository companhiaRepository;

    public Usuario cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) throws BusinessException {
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(cadastroUsuarioDTO.email());

        if(usuarioByEmail.isPresent()){
            throw new BusinessException("O e-mail informado já está em uso. Por favor, utilize um e-mail diferente.");
        }

        String senhaCriptografada = securityConfig.passwordEncoder().encode(cadastroUsuarioDTO.senha());
        Optional<Companhia> companhia = companhiaRepository.findById(cadastroUsuarioDTO.idCompanhia());

        if(!companhia.isPresent()){
            throw new BusinessException("A companhia informada não existe");
        }


        Usuario usuario = new Usuario(cadastroUsuarioDTO, senhaCriptografada, companhia.get());
        usuarioRepository.save(usuario);

        companhia.get().getUsuarios().add(usuario);
        companhiaRepository.save(companhia.get());

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obterPorCodigo(Long codigoUsuario) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findById(codigoUsuario);

        if(!usuario.isPresent()){
            throw new BusinessException("Usuario não encontrado");
        }

        return usuario.get();
    }

    public Usuario alterarUsuario(Long codigoUsuario, AlterarUsuarioDTO alterarUsuarioDTO) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findById(codigoUsuario);

        if(!usuario.isPresent()){
            throw new BusinessException("Usuario não encontrado");
        }

        usuario.get().alterar(alterarUsuarioDTO);
        return usuarioRepository.save(usuario.get());
    }

    public void deletarUsuario(Long codigoUsuario){
        usuarioRepository.deleteById(codigoUsuario);
    }
}
