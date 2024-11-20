package com.fiap.guardian_bff.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserAuthenticatorFilter userAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        List<AntPathRequestMatcher> listaDeUrlsLiberadas = carregarListaUrlsLiberadas();

        http.authorizeHttpRequests((authorize) -> {
            for (AntPathRequestMatcher e : listaDeUrlsLiberadas) {
                authorize.requestMatchers(e).permitAll();
            }
            authorize.requestMatchers("/companhia/**").hasRole("ADMIN");
            authorize.requestMatchers("/usuario/listar-todos").hasRole("ADMIN");
            authorize.requestMatchers("/alertas/listar-todos").hasRole("ADMIN");

            authorize.anyRequest().authenticated();
        });

        http.headers(httpSecurityHeadersConfigurer -> {
            httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig ->
                    frameOptionsConfig.sameOrigin().httpStrictTransportSecurity(d ->
                            d.includeSubDomains(true).maxAgeInSeconds(31536000)));
        });

        http.formLogin(AbstractHttpConfigurer::disable);

        http.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Token inválido ou expirado\"}");
                    })
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Acesso proibido: Requer permissão adicional\"}");
                    });
        });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected List<AntPathRequestMatcher> carregarListaUrlsLiberadas() {
        List<AntPathRequestMatcher> listaDeUrlsLiberadas = new ArrayList<>();
        listaDeUrlsLiberadas.add(new AntPathRequestMatcher("/actuator"));
        listaDeUrlsLiberadas.add(new AntPathRequestMatcher("/swagger-ui/index.html"));
        listaDeUrlsLiberadas.add(new AntPathRequestMatcher("/swagger-ui/**"));
        listaDeUrlsLiberadas.add(new AntPathRequestMatcher("/api-docs/**"));
        listaDeUrlsLiberadas.add(new AntPathRequestMatcher("/login"));
        return listaDeUrlsLiberadas;
    }
}

