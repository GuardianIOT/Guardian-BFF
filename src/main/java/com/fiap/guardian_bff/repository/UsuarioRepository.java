package com.fiap.guardian_bff.repository;

import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByCompanhia(Companhia companhia);
}
