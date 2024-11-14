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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class UserAuthenticatorFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!verificaUrlsLiberadas(request)){
            String token = recoveryToken(request);
            if (token != null) {
                String usuario = tokenService.getSubjectFromToken(token);
                Usuario user = userRepository.findByEmail(usuario).get();
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getSenha(), null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write( "{\"error\": \"Token ausente\"}");
            }
        }else{
            filterChain.doFilter(request, response);
        }

    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }


    private boolean verificaUrlsLiberadas(HttpServletRequest request){
        SecurityConfig securityConfig = new SecurityConfig();
        List<AntPathRequestMatcher> urlsLiberadas = securityConfig.carregarListaUrlsLiberadas();
        String requestUrl = request.getRequestURI();

        for(AntPathRequestMatcher urlLiberada : urlsLiberadas){
            if(requestUrl.contains(urlLiberada.getPattern())
                    || requestUrl.contains("/api-docs")
                    || requestUrl.contains("swagger-ui")
            ){
                return true;
            }
        }

        return false;
    }
}
