# ACTIONS

Log cronológico de trabajo en el proyecto.

## 2026-11-13 — Etapa 1: REST API en memoria

- Agregadas dependencias `spring-boot-starter-web` y `spring-boot-starter-validation`.
- Creado paquete `com.example.springtest.gymclass`:
  - `GymClass` — modelo de dominio.
  - `dto/CreateGymClassRequest`, `dto/GymClassResponse` — records con validación
    (`@NotBlank`, `@Positive`, `@NotNull`).
  - `GymClassService` (interfaz) + `GymClassServiceImpl` — almacenamiento en
    memoria (`ConcurrentHashMap` + `AtomicLong`).
  - `GymClassController` — `POST /api/classes`, `GET /api/classes`,
    `GET /api/classes/{id}`.
  - `GymClassNotFoundException` + `GymClassExceptionHandler`.
- Probados los 3 endpoints manualmente con `curl` (201/200/404/400 por validación).
- `mvn test` en verde.

## 2026-11-13 — Upgrade de versión Spring Boot

- Detectado que el proyecto estaba en Spring Boot 3.3.2 (jul 2024, EOL).
- Evaluadas alternativas (4.1.x vs 3.5.x). Se eligió **3.5.16** — última build
  open-source disponible en Maven Central de la rama 3.5 (la rama completa llegó
  a EOL OSS el 30/jun/2026; parches posteriores como 3.5.21 son solo soporte
  comercial Broadcom/VMware).
- Verificado build + tests en verde tras el bump.

## 2026-11-13 — `ProblemDetail` en el manejo de errores

- Reemplazado `ResponseEntity<String>` por `ProblemDetail` (RFC 7807) en
  `GymClassExceptionHandler`. Verificado `Content-Type: application/problem+json`
  con body `type/title/status/detail/instance`.

## 2026-11-13/16 — Etapa 2: persistencia con JPA + Postgres + Flyway

Decisiones tomadas con el usuario antes de implementar (ver `PRD-updated.md`):
Postgres vía Docker Compose, alcance solo `GymClass`, Flyway para schema,
Testcontainers para tests de integración.

- `docker-compose.yml` — servicio `postgres:16-alpine` (db/user/pass `gymclasses`,
  puerto 5432).
- Agregadas dependencias: `spring-boot-starter-data-jpa`, `flyway-database-postgresql`,
  `org.postgresql:postgresql`, `spring-boot-testcontainers`,
  `testcontainers:junit-jupiter`, `testcontainers:postgresql`.
- `application.properties` — datasource a Postgres local, `ddl-auto=validate`.
- `V1__create_gym_class_table.sql` — tabla `gym_class`.
- `GymClass` convertida a `@Entity` (constructor no-arg protegido para JPA, id
  generado por la DB vía `IDENTITY`).
- `GymClassRepository extends JpaRepository<GymClass, Long>`.
- `GymClassServiceImpl` reescrito para usar el repositorio en vez del `Map` en
  memoria; `@Transactional` en escritura, `@Transactional(readOnly = true)` en
  lecturas.
- `GymClassRepositoryTest` — `@DataJpaTest` + `@AutoConfigureTestDatabase(replace = NONE)`
  + Testcontainers (`@Container` + `@ServiceConnection` sobre `PostgreSQLContainer`).
- Verificado extremo a extremo: Flyway migra al arrancar, endpoints funcionan
  contra Postgres real, datos persisten entre reinicios de la app.
- Agregada query custom: `GymClassRepository.findByInstructor` (`@Query` JPQL,
  case-insensitive), expuesta como filtro opcional `GET /api/classes?instructor=`.
- `mvn clean test` → 5/5 tests, `BUILD SUCCESS`.
- `tidy:pom` corrido sobre `pom.xml` tras los cambios de dependencias.

## 2026-11-16 — Control de versiones

- Verificado que el repo `nicolasbettiol10/mygymapp` en GitHub existía vacío.
- `git init`, remoto `origin` apuntando a ese repo, rama `main`.
- Cambiada la identidad **global** de git: de `nbettiol@pavlovmedia.com` (sin
  `user.name`) a `Nicolas Bettiol <nicolasbettiol@gmail.com>` — el usuario ya no
  trabaja en el empleo del email anterior.
- Borrada una credencial vieja de GitHub cacheada en el keychain de macOS que
  rompía el push con "Invalid username or token".
- Usuario hizo el push inicial (`git push -u origin main`) autenticando con
  Personal Access Token directamente en su terminal, sin pasar el token por el
  chat.
- Creados `PRD.md`, `PRD-updated.md`, `TODO.md`, `ACTIONS.md` para que cualquier
  sesión nueva tenga contexto completo del proyecto sin depender de la
  conversación anterior.
