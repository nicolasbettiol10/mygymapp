# TODO

| # | Etapa | Tarea | Estado |
|---|-------|-------|--------|
| 1 | 1 | `POST /api/classes` — crear clase (en memoria) | Completo |
| 2 | 1 | `GET /api/classes` — listar clases | Completo |
| 3 | 1 | `GET /api/classes/{id}` — buscar por id (200/404) | Completo |
| 4 | 1 | Separación Controller/Service, DTOs, validación capacidad > 0 | Completo |
| 5 | 1 | Manejo de errores con `ProblemDetail` (RFC 7807) | Completo |
| 6 | 2 | Migrar `GymClass` a entidad JPA | Completo |
| 7 | 2 | Repositorio `GymClassRepository` (Spring Data JPA) | Completo |
| 8 | 2 | Docker Compose con PostgreSQL | Completo |
| 9 | 2 | Migraciones con Flyway (`V1__create_gym_class_table.sql`) | Completo |
| 10 | 2 | `@Transactional` en el service (escritura + lecturas readOnly) | Completo |
| 11 | 2 | Test de integración con Testcontainers (`GymClassRepositoryTest`) | Completo |
| 12 | 2 | Query custom `findByInstructor` (`@Query` JPQL) + filtro en `GET /api/classes?instructor=` | Completo |
| 13 | - | Setup de git/GitHub (`nicolasbettiol10/mygymapp`) | Completo |
| 14 | - | `PRD.md`, `PRD-updated.md`, `TODO.md`, `ACTIONS.md` | Completo |
| 15 | 2 | Relaciones JPA (`User`, `Reservation`) | Pendiente — se hace en Etapas 3/4 |
| 16 | 3 | Entidad `User` + registro/login | Pendiente |
| 17 | 3 | JWT (emisión y validación) | Pendiente |
| 18 | 3 | Spring Security — permisos admin vs cliente | Pendiente |
| 19 | 4 | Entidad `Reservation` + reservar/cancelar | Pendiente |
| 20 | 4 | Control de concurrencia (evitar sobreventa de cupos) | Pendiente |
| 21 | 4 | Lista de espera | Pendiente |
| 22 | 5 | Eventos con Kafka | Pendiente |
| 23 | 5 | Cache con Redis | Pendiente |
| 24 | 5 | Scheduled jobs | Pendiente |
| 25 | 5 | Observabilidad con Actuator | Pendiente |
