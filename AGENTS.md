# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de un servicio REST en un dominio de banca**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.9.1
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del entorno y enrutamiento de solicitudes**: Entorno de desarrollo configurado y solicitudes REST enrutadas al servicio.
- **Fase 2 — Implementación de la lógica de negocio**: Lógica de negocio implementada para consultar saldos y realizar transferencias.
- **Fase 3 — Generación de logs y conexión a la base de datos**: Logs generados para cada operación y conexión a la base de datos establecida.
- **Fase 4 — Registro de operaciones en el sistema de auditoría**: Operaciones registradas en el sistema de auditoría.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/bank/digital/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (54)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/digital/DigitalBankApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/application/AccountService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/config/SwaggerConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getId`
      Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setId`
      Se invoca `setId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setCreatedAt`
      Se invoca `setCreatedAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getAccountNumber`
      Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getCustomerId`
      Se invoca `getCustomerId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setLastUpdated`
      Se invoca `setLastUpdated` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setActive`
      Se invoca `setActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.isActive`
      Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getBalance`
      Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/AccountService.java` — `Account.isActive`
      Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/AccountService.java` — `Account.getBalance`
      Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/AccountService.java` — `Account.getId`
      Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Transfer.setStatus`
      Se invoca `setStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Transfer.getId`
      Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Account.getBalance`
      Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Account.getAccountNumber`
      Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getId`
      Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getAccountNumber`
      Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getBalance`
      Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getCurrency`
      Se invoca `getCurrency` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.isActive`
      Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.sourceAccountId`
      Se invoca `sourceAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.targetAccountId`
      Se invoca `targetAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.amount`
      Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.currency`
      Se invoca `currency` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.reference`
      Se invoca `reference` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getId`
      Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getSourceAccountId`
      Se invoca `getSourceAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getTargetAccountId`
      Se invoca `getTargetAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getAmount`
      Se invoca `getAmount` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getCurrency`
      Se invoca `getCurrency` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getStatus`
      Se invoca `getStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getTransferDate`
      Se invoca `getTransferDate` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `AuditLogRepository.save`
      Se invoca `save` sobre `AuditLogRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `AuditEntry.id`
      Se invoca `id` sobre `AuditEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `TransferServiceImpl.transfer`
      Se invoca `transfer` sobre `TransferServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getStatus`
      Se invoca `getStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getAmount`
      Se invoca `getAmount` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getSourceAccountId`
      Se invoca `getSourceAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getTargetAccountId`
      Se invoca `getTargetAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Account.getBalance`
      Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getTransferDate`
      Se invoca `getTransferDate` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getId`
      Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getReference`
      Se invoca `getReference` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/digital/infrastructure/adapters/AccountControllerTest.java` — `TransferServiceImpl.transfer`
      Se invoca `transfer` sobre `TransferServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/bank/digital/DigitalBankApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/digital/domain/model/Account.java`
- `src/main/java/com/bank/digital/domain/model/Transfer.java`
- `src/main/java/com/bank/digital/domain/ports/AccountRepository.java`
- `src/main/java/com/bank/digital/domain/ports/AuditService.java`
- `src/main/java/com/bank/digital/domain/ports/TransferService.java`
- `src/main/java/com/bank/digital/domain/exception/InsufficientBalanceException.java`
- `src/main/java/com/bank/digital/domain/exception/CoreBankingException.java`
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java`
- `src/main/java/com/bank/digital/application/AccountService.java`
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java`
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java`
- `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java`
- `src/main/java/com/bank/digital/infrastructure/config/ResilienceConfig.java`
- `src/main/java/com/bank/digital/infrastructure/config/SwaggerConfig.java`
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java`
- `src/test/java/com/bank/digital/infrastructure/adapters/AccountControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/digital`
- `src/main/java/com/bank/digital/application`
- `src/main/java/com/bank/digital/domain`
- `src/main/java/com/bank/digital/domain/model`
- `src/main/java/com/bank/digital/domain/ports`
- `src/main/java/com/bank/digital/domain/exception`
- `src/main/java/com/bank/digital/infrastructure`
- `src/main/java/com/bank/digital/infrastructure/adapters`
- `src/main/java/com/bank/digital/infrastructure/config`
- `src/main/java/com/bank/digital/infrastructure/persistence`
- `src/main/java/com/bank/digital/infrastructure/audit`
- `src/main/resources`
- `src/test/java/com/bank/digital`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Chapter Backend, Especialidad Desarrollador, Tecnolog�a Java, Advanced � Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programaci�n principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
