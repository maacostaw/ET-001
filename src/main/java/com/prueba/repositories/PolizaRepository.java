package com.prueba.repositories;

import com.prueba.entities.PolizaEntity;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.TipoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PolizaRepository extends JpaRepository<PolizaEntity, Long> {
    @Query("SELECT p FROM PolizaEntity p WHERE (:tipo IS NULL OR p.tipoPoliza = :tipo) AND (:estado IS NULL OR p.estadoPoliza = :estado)")

    List<PolizaEntity> buscarPorTipoYEstado(TipoPoliza tipo, EstadoPoliza estado);
}
