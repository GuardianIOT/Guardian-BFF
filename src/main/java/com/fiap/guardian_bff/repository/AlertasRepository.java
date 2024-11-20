package com.fiap.guardian_bff.repository;

import com.fiap.guardian_bff.model.Alerta;
import com.fiap.guardian_bff.model.Companhia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertasRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findAllByCompanhia(Companhia companhia);
}
