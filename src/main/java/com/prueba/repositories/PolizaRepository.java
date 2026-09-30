package com.prueba.repositories;

import com.prueba.entities.PolizaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolizaRepository extends JpaRepository<PolizaEntity, Long> {
}
