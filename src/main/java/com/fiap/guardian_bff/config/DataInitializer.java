package com.fiap.guardian_bff.config;

import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.repository.CompanhiaRepository;
import com.fiap.guardian_bff.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Configuration
public class DataInitializer {

        private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private CompanhiaRepository companhiaRepository;

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @PostConstruct
        public void init() {
            Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail("admin@email.com");

            if (!usuarioExistente.isPresent()) {
                Companhia companhia = new Companhia("Companhia",
                        "12.015.104/0001-59",
                        11988553066L,
                        "Endereço",
                        "companhia@email.com");

                Companhia companhiaSave = companhiaRepository.save(companhia);

                ArrayList<Usuario> users = new ArrayList<>();

                Usuario admin = new Usuario("Admin",
                        "User",
                        "admin@email.com",
                        passwordEncoder.encode("admin@123"),
                        companhiaSave,
                        new HashSet<>(Collections.singletonList("ROLE_ADMIN")));
                users.add(admin);
                Usuario user = new Usuario("User",
                        "",
                        "user@email.com",
                        passwordEncoder.encode("user@123"),
                        companhiaSave,
                        new HashSet<>(Collections.singletonList("ROLE_USER")));
                users.add(user);
                usuarioRepository.saveAll(users);
            }
        }
}

