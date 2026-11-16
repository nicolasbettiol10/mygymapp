# PRD actualizado — decisiones y requerimientos aprendidos

Este documento registra decisiones tomadas durante la implementación que no
estaban en el `PRD.md` original, o que lo precisan.

## Versiones

- Spring Boot fijado en **3.5.16** (última build open-source de la rama 3.5;
  la 3.5 completa llegó a EOL OSS el 30/jun/2026, parches posteriores son solo
  soporte comercial). Se evaluó pasar a Boot 4.x pero se decidió quedarse en una
  rama más conservadora para este aprendizaje.
- Java 21, Spring Framework resuelto transitivamente vía el BOM de Boot (6.2.19
  al momento de escribir esto).

## Base de datos (Etapa 2)

- Motor: **PostgreSQL vía Docker Compose** (no H2), para practicar con el motor
  real desde el principio — tipos de datos y locking reales, relevante para la
  Etapa 4 de concurrencia.
- Alcance acotado: en la Etapa 2 **solo se migró `GymClass`** a JPA. `User` y
  `Reservation` quedan para las Etapas 3 y 4 respectivamente, según el roadmap
  original.
- Gestión de schema: **Flyway**, no `ddl-auto=update`. `spring.jpa.hibernate.ddl-auto=validate`
  — Hibernate valida contra lo que Flyway ya aplicó, nunca genera schema solo.
- Testing de repositorios: **Testcontainers** con Postgres real (no H2), vía
  `@ServiceConnection` — evita tener que mantener `@DynamicPropertySource` a mano
  y asegura que los tests corren contra el mismo motor que producción.

## Manejo de errores

- Los `@ExceptionHandler` devuelven `ProblemDetail` (RFC 7807), no `String` plano
  ni un wrapper custom.

## Control de versiones

- Repo: https://github.com/nicolasbettiol10/mygymapp (rama `main`).
- Identidad de git para este proyecto: se cambió la identidad **global** de la
  máquina (no solo local al repo) de `nbettiol@pavlovmedia.com` a
  `Nicolas Bettiol <nicolasbettiol@gmail.com>`, porque el usuario ya no trabaja
  en el empleo asociado al email anterior.
- Auth hacia GitHub: HTTPS + Personal Access Token (no hay SSH key para la
  cuenta `nicolasbettiol10`, ni `gh` CLI instalado).

## Arquitectura / alcance

- Las entidades JPA (`@Entity`) **no se comparten entre servicios** — si en el
  futuro hace falta que otro servicio use datos de `GymClass`, se expone vía API
  REST o eventos (Kafka, Etapa 5), no vía la entidad de persistencia directamente.
  Separar en módulos Maven (`api`, `core`, etc.) solo tendría sentido si aparece
  un segundo servicio real que lo necesite — no se hizo ahora para evitar
  abstracción prematura.
