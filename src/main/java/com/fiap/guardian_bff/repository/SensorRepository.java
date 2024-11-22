package com.fiap.guardian_bff.repository;

import com.fiap.guardian_bff.model.SensorClima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorClima, Long> {

}
