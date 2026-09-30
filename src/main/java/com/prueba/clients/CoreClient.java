package com.prueba.clients;

import com.prueba.dtos.CoreEventoDTO;
import com.prueba.enums.TipoEventoCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class CoreClient {
    private static final Logger log = LoggerFactory.getLogger(CoreClient.class);

    private final RestClient restClient;

    public CoreClient(@Value("${core.url}") String coreUrl,
                      @Value("${x.api.key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl(coreUrl)
                .defaultHeader("x-api-key", apiKey)
                .build();
    }

    public void notificar(Long polizaId) {
        CoreEventoDTO evento = new CoreEventoDTO(TipoEventoCore.ACTUALIZACION, polizaId);
        try {
            log.info("Enviando evento {} al CORE para póliza {}", evento.getEvento(), polizaId);
            restClient.post()
                    .uri("/core-mock/evento")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(evento)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException e) {
            log.error("No se pudo notificar al CORE para póliza {}: {}", polizaId, e.getMessage());
        }
    }
}