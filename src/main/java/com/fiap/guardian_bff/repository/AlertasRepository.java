package com.fiap.guardian_bff.repository;

import com.fiap.guardian_bff.model.Alerta;
import com.fiap.guardian_bff.model.Companhia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertasRepository extends JpaRepository<Alerta, Long> {

    Page<Alerta> findAllByCompanhia(Companhia companhia, Pageable pageable);

    @Override
    Page<Alerta> findAll(Pageable pageable);
}
