package com.prueba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiKeyInvalidaException.class)
    public ResponseEntity<Map<String, Object>> ApiKeyInvalidaHandler(ApiKeyInvalidaException e) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        response.put("Timestamp", LocalDateTime.now());
        response.put("Status", status);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ReglaDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> ReglaDeNegocioHandler(ReglaDeNegocioException e) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.CONFLICT;
        response.put("Timestamp", LocalDateTime.now());
        response.put("Status", status);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> RecursoNoEncontradoHandler(RecursoNoEncontradoException e){
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;
        response.put("Timestamp", LocalDateTime.now());
        response.put("Status", status);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> typeMismatchHandler(MethodArgumentTypeMismatchException e) {
        String mensaje = "Valor inválido '" + e.getValue() + "' para el parámetro '" + e.getName() + "'";

        Class<?> tipo = e.getRequiredType();
        if (tipo != null && tipo.isEnum()) {
            mensaje += ". Valores permitidos: " + Arrays.toString(tipo.getEnumConstants());
        }

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        response.put("Timestamp", LocalDateTime.now());
        response.put("Status", status);
        response.put("Message", mensaje);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> exceptionHandler(Exception e){
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        response.put("Timestamp", LocalDateTime.now());
        response.put("Status", status);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(status).body(response);
    }
}
