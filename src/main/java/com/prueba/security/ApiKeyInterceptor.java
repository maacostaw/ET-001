package com.prueba.security;

import com.prueba.exceptions.ApiKeyInvalidaException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    private static final String HEADER = "x-api-key";

    private final byte[] apiKey;

    public ApiKeyInterceptor(@Value("${x.api.key}") String apiKey) {
        this.apiKey = apiKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String recibida = request.getHeader(HEADER);

        if (recibida == null || recibida.isBlank()) {
            throw new ApiKeyInvalidaException("Falta el header obligatorio '" + HEADER + "'");
        }
        if (!MessageDigest.isEqual(apiKey, recibida.getBytes(StandardCharsets.UTF_8))) {
            throw new ApiKeyInvalidaException("API key inválida");
        }
        return true;
    }
}