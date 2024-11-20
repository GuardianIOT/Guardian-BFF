package com.fiap.guardian_bff.config;

import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.repository.UsuarioRepository;
import com.fiap.guardian_bff.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserAuthenticatorFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);

        if (token != null) {
            String usuarioEmail = tokenService.getSubjectFromToken(token);
            Optional<Usuario> usuarioOpt = userRepository.findByEmail(usuarioEmail);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(),
                        null,
                        usuario.getRoles().stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}

