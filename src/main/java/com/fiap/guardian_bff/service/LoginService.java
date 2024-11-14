package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.dto.oauth.RealizarLoginDTO;
import com.fiap.guardian_bff.dto.oauth.TokenDTO;
import com.fiap.guardian_bff.exceptions.AuthenticationException;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    public TokenDTO realizarLogin(RealizarLoginDTO realizarLoginDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(realizarLoginDTO.email());

        if(usuario.isEmpty()) throw new AuthenticationException("Email ou senha inválidos!");
        if(!BCrypt.checkpw(realizarLoginDTO.senha(), usuario.get().getSenha())) throw new AuthenticationException("Email ou senha inválidos!");

        return new TokenDTO(tokenService.generateToken(usuario.get()));
    }
}
