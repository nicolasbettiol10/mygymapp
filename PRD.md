# PRD — Gym Booking App

## Idea

Aplicación donde gimnasios o centros deportivos publican sus clases y los usuarios
reservan lugares. Ejemplo: un gimnasio tiene una clase de spinning a las 20:00 con
10 bicicletas disponibles. Los usuarios pueden reservar, cancelar y entrar en lista
de espera cuando se completa el cupo.

Conecta con la idea de intermediación de servicios explorada anteriormente.

## Stack tecnológico

- **Backend**: Java 21, Spring Boot, Spring MVC
- **Persistencia**: PostgreSQL, Spring Data JPA, Hibernate, Flyway
- **Seguridad**: Spring Security, JWT
- **Testing**: JUnit 5, Mockito, Testcontainers
- **Infraestructura**: Docker Compose, Maven
- **Frontend**: React + TypeScript (más adelante)

## Repositorio

https://github.com/nicolasbettiol10/mygymapp

## Roadmap por etapas

### Etapa 1 — REST API y arquitectura Spring
Crear clases, usuarios y reservas. `@RestController`, `@Service`, inyección de
dependencias, DTOs, validación y manejo de excepciones.

**Ejercicio 1 — Crear y consultar clases** (alcance real de la Etapa 1, en memoria):
- `POST /api/classes` → 201 + clase creada con ID. Body: `name`, `instructor`,
  `capacity` (> 0), `startTime`, `durationMinutes`.
- `GET /api/classes` → 200 + todas las clases.
- `GET /api/classes/{id}` → 200 si existe, 404 si no.
- Separación Controller/Service, inyección por constructor, DTOs, validación de
  capacidad > 0.

### Etapa 2 — Base de datos y transacciones
Relaciones JPA, repositorios, consultas, `@Transactional`, migraciones con Flyway.

### Etapa 3 — Seguridad
Registro, login, JWT, Spring Security, permisos para administradores y clientes.

### Etapa 4 — Concurrencia y lógica real
¿Qué pasa si dos usuarios reservan la última bicicleta a la vez? Resolver con
transacciones y locking para evitar sobreventa. Listas de espera.

### Etapa 5 — Arquitectura avanzada
Eventos con Kafka, cache con Redis, scheduled jobs, testing de integración,
observabilidad con Actuator.

## Modo de trabajo

El usuario actúa como tech lead: da los requerimientos, Claude implementa y
los revisan juntos. Antes de implementar decisiones no triviales (elección de
tecnología, alcance), Claude debe preguntar en vez de asumir.
