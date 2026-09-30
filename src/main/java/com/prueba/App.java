package com.prueba;

import com.prueba.entities.PolizaEntity;
import com.prueba.entities.RiesgoEntity;
import com.prueba.enums.EstadoPoliza;
import com.prueba.enums.EstadoRiesgo;
import com.prueba.enums.TipoPoliza;
import com.prueba.repositories.PolizaRepository;
import com.prueba.repositories.RiesgoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class
App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner seedData(PolizaRepository polizaRepository,
                                      RiesgoRepository riesgoRepository) {
        return args -> {
            // Individual activa con su único riesgo
            PolizaEntity individual = polizaRepository.save(new PolizaEntity(
                    TipoPoliza.INDIVIDUAL,
                    LocalDateTime.of(2026, 1, 1, 0, 0),
                    LocalDateTime.of(2027, 1, 1, 0, 0),
                    1500000,
                    new BigDecimal("18000000"),
                    "Carlos Pérez"));
            riesgoRepository.save(new RiesgoEntity(individual, "Carlos Pérez", "Ana Gómez"));

            // Colectiva activa con dos riesgos
            PolizaEntity colectiva = polizaRepository.save(new PolizaEntity(
                    TipoPoliza.COLECTIVA,
                    LocalDateTime.of(2026, 3, 1, 0, 0),
                    LocalDateTime.of(2027, 3, 1, 0, 0),
                    2000000,
                    new BigDecimal("24000000"),
                    "Inmobiliaria Los Andes"));
            riesgoRepository.save(new RiesgoEntity(colectiva, "Laura Díaz", "Pedro Ruiz"));
            riesgoRepository.save(new RiesgoEntity(colectiva, "Jorge Mora", "Pedro Ruiz"));

            // Individual cancelada, para probar que no se puede renovar
            polizaRepository.save(new PolizaEntity(
                    TipoPoliza.INDIVIDUAL,
                    LocalDateTime.of(2025, 6, 1, 0, 0),
                    LocalDateTime.of(2026, 6, 1, 0, 0),
                    1200000,
                    new BigDecimal("14400000"),
                    "Sofía Rojas"));
        };
    }
}
