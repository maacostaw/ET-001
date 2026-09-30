# ET-001 · API de Gestión de Pólizas

API REST en Spring Boot con arquitectura por capas (controller, service, repository) y base de datos H2 en memoria.

## Cómo correr

**Requisitos:** Java 17+ y Maven.

```bash
mvn spring-boot:run
```

O desde IntelliJ: abrir `App.java` y ejecutar el `main`.

La API queda en `http://localhost:8080`. Al arrancar se cargan datos de prueba (`DataSeeder`), y se reinician en cada ejecución.

**Todas las peticiones requieren el header:**

```
api-key: 123456
```

Consola de H2: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:polizasdb`, usuario `sa`, sin contraseña).

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/polizas?tipo=&estado=` | Lista pólizas. Filtros opcionales: `tipo` (`INDIVIDUAL`, `COLECTIVA`) y `estado` (`ACTIVA`, `RENOVADA`, `CANCELADA`) |
| GET | `/polizas/{id}/riesgos` | Lista los riesgos de una póliza |
| POST | `/polizas/{id}/renovar` | Incrementa canon y prima según IPC, corre la vigencia y pasa a `RENOVADA` |
| POST | `/polizas/{id}/cancelar` | Cancela la póliza y todos sus riesgos |
| POST | `/polizas/{id}/riesgos` | Agrega un riesgo (solo pólizas colectivas) |
| POST | `/riesgos/{id}/cancelar` | Cancela un riesgo (solo pólizas colectivas) |
| POST | `/core-mock/evento` | Mock del CORE: registra en logs el evento recibido |

## Ejemplo rápido

Cancelar la póliza 2:

```bash
curl -X POST http://localhost:8080/polizas/2/cancelar -H "api-key: 123456"
```

Responde `200` con la póliza en estado `CANCELADA`.

Intentar renovarla:

```bash
curl -X POST http://localhost:8080/polizas/2/renovar -H "api-key: 123456"
```

Responde `409`, porque no se puede renovar una póliza cancelada:

```json
{
  "Timestamp": "2026-09-30T01:50:00",
  "Status": "CONFLICT",
  "Message": "No se puede renovar una póliza cancelada"
}
```
