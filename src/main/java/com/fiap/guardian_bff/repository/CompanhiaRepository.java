package com.fiap.guardian_bff.repository;

import com.fiap.guardian_bff.model.Companhia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanhiaRepository extends JpaRepository<Companhia, Long> {
}
