package com.prueba.repositories;

import com.prueba.entities.RiesgoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiesgoRepository extends JpaRepository<RiesgoEntity, Long> {
}
