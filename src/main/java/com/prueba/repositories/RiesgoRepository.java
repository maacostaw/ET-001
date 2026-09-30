package com.prueba.repositories;

import com.prueba.entities.RiesgoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiesgoRepository extends JpaRepository<RiesgoEntity, Long> {
    List<RiesgoEntity> findByPolizaEntityId(Long polizaId);
}
