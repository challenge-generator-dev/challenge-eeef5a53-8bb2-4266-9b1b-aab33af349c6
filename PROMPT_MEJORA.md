# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/bank/digital/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/digital/DigitalBankApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/application/AccountService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/config/SwaggerConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getId`: Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setId`: Se invoca `setId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setCreatedAt`: Se invoca `setCreatedAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getAccountNumber`: Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getCustomerId`: Se invoca `getCustomerId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setLastUpdated`: Se invoca `setLastUpdated` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.setActive`: Se invoca `setActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.isActive`: Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java` — `Account.getBalance`: Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/AccountService.java` — `Account.isActive`: Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/AccountService.java` — `Account.getBalance`: Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/AccountService.java` — `Account.getId`: Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Transfer.setStatus`: Se invoca `setStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Transfer.getId`: Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Account.getBalance`: Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/application/TransferServiceImpl.java` — `Account.getAccountNumber`: Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getId`: Se invoca `getId` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getAccountNumber`: Se invoca `getAccountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getBalance`: Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.getCurrency`: Se invoca `getCurrency` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Account.isActive`: Se invoca `isActive` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.sourceAccountId`: Se invoca `sourceAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.targetAccountId`: Se invoca `targetAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.amount`: Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.currency`: Se invoca `currency` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.reference`: Se invoca `reference` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `TransferRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getId`: Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getSourceAccountId`: Se invoca `getSourceAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getTargetAccountId`: Se invoca `getTargetAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getAmount`: Se invoca `getAmount` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getCurrency`: Se invoca `getCurrency` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getStatus`: Se invoca `getStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java` — `Transfer.getTransferDate`: Se invoca `getTransferDate` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `AuditLogRepository.save`: Se invoca `save` sobre `AuditLogRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java` — `AuditEntry.id`: Se invoca `id` sobre `AuditEntry`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `TransferServiceImpl.transfer`: Se invoca `transfer` sobre `TransferServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getStatus`: Se invoca `getStatus` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getAmount`: Se invoca `getAmount` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getSourceAccountId`: Se invoca `getSourceAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getTargetAccountId`: Se invoca `getTargetAccountId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Account.getBalance`: Se invoca `getBalance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getTransferDate`: Se invoca `getTransferDate` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getId`: Se invoca `getId` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/application/TransferServiceImplTest.java` — `Transfer.getReference`: Se invoca `getReference` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/digital/infrastructure/adapters/AccountControllerTest.java` — `TransferServiceImpl.transfer`: Se invoca `transfer` sobre `TransferServiceImpl`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Chapter Backend, Especialidad Desarrollador, Tecnolog�a Java, Advanced � Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programaci�n principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de un servicio REST en un dominio de banca
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del entorno y enrutamiento de solicitudes — objetivo: Configurar el entorno de desarrollo y enrutar las solicitudes REST al servicio. — entregable (NO resolver): Entorno de desarrollo configurado y solicitudes REST enrutadas al servicio.
- Fase 2: Implementación de la lógica de negocio — objetivo: Implementar la lógica de negocio para consultar el saldo y realizar transferencias. — entregable (NO resolver): Lógica de negocio implementada para consultar saldos y realizar transferencias.
- Fase 3: Generación de logs y conexión a la base de datos — objetivo: Generar logs para cada operación y conectar el servicio a la base de datos utilizando un ORM. — entregable (NO resolver): Logs generados para cada operación y conexión a la base de datos establecida.
- Fase 4: Registro de operaciones en el sistema de auditoría — objetivo: Registrar cada operación realizada por el servicio en el sistema de auditoría. — entregable (NO resolver): Operaciones registradas en el sistema de auditoría.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bank</groupId>
    <artifactId>digital-bank</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>digital-bank</name>
    <description>Servicio REST de banca digital con manejo de transferencias y auditoría</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <springdoc-openapi.version>2.9.1</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>central</id>
            <url>https://repo.maven.apache.org/maven2</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/bank/digital/DigitalBankApplication.java ===
package com.bank.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.util.function.Supplier;

@SpringBootApplication
@EnableConfigurationProperties
@EnableRetry
@EnableAsync
@ComponentScan(basePackages = {"com.bank.digital"})
public class DigitalBankApplication {
    private static final Logger logger = LoggerFactory.getLogger(DigitalBankApplication.class);

    @Value("${core.banking.base-url}")
    private String coreBankingBaseUrl;

    @Value("${resilience4j.retry.max-attempts}")
    private int maxRetryAttempts;

    @Value("${resilience4j.circuitbreaker.failure-rate-threshold}")
    private float failureRateThreshold;

    @Value("${resilience4j.circuitbreaker.wait-duration-in-open-state}")
    private long waitDurationInOpenState;

    public static void main(String[] args) {
        try {
            var context = SpringApplication.run(DigitalBankApplication.class, args);
            logger.info("Digital Bank Application started successfully");
            context.getBean(DigitalBankApplication.class).validateConfiguration();
        } catch (Exception e) {
            logger.error("Failed to start Digital Bank Application", e);
            throw e;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .build();
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(maxRetryAttempts)
                .waitDuration(Duration.ofSeconds(1))
                .build();
        return RetryRegistry.of(retryConfig);
    }

    private void validateConfiguration() {
        if (coreBankingBaseUrl == null || coreBankingBaseUrl.isBlank()) {
            throw new IllegalStateException("core.banking.base-url must be configured");
        }
        if (maxRetryAttempts <= 0) {
            throw new IllegalStateException("resilience4j.retry.max-attempts must be positive");
        }
        if (failureRateThreshold <= 0 || failureRateThreshold > 100) {
            throw new IllegalStateException("resilience4j.circuitbreaker.failure-rate-threshold must be between 0 and 100");
        }
        if (waitDurationInOpenState <= 0) {
            throw new IllegalStateException("resilience4j.circuitbreaker.wait-duration-in-open-state must be positive");
        }
        logger.info("Configuration validation passed");
    }

    @Bean
    public Supplier<String> coreBankingHealthCheck() {
        return () -> {
            try {
                RestTemplate restTemplate = restTemplate();
                String healthUrl = coreBankingBaseUrl + "/health";
                String response = restTemplate.getForObject(healthUrl, String.class);
                logger.debug("Core banking health check response: {}", response);
                return response != null && response.contains("UP") ? "UP" : "DOWN";
            } catch (Exception e) {
                logger.error("Core banking health check failed", e);
                return "DOWN";
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: digital-bank
  datasource:
    url: jdbc:postgresql://localhost:5432/digital_bank
    username: digital_bank_user
    password: ${DB_PASSWORD:securepassword}
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 2
      idle-timeout: 30000
      connection-timeout: 30000
      validation-timeout: 5000
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
    open-in-view: false
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

server:
  port: 8080
  servlet:
    context-path: /api/v1
  error:
    include-message: always
    include-binding-errors: always
    include-exception: false
    include-stacktrace: never

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
  health:
    livenessstate:
      enabled: true
    readinessstate:
      enabled: true

resilience4j:
  circuitbreaker:
    configs:
      default:
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        failureRateThreshold: 50
        waitDurationInOpenState: 5000
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
    instances:
      coreBanking:
        baseConfig: default
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 1000
        retryExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
    instances:
      coreBanking:
        baseConfig: default

logging:
  level:
    root: INFO
    com.bank.digital: DEBUG
    org.springframework.web: INFO
    org.hibernate: ERROR
    io.github.resilience4j: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/digital-bank.log

core:
  banking:
    base-url: http://localhost:9090/core-banking
    timeout: 2000

// === ARCHIVO: src/main/java/com/bank/digital/domain/model/Account.java ===
package com.bank.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @NotNull(message = "Account ID cannot be null")
    private UUID id;

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotNull(message = "Currency cannot be null")
    private String currency;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Last updated date cannot be null")
    private LocalDateTime lastUpdated;

    private boolean active;

    public Account(UUID customerId, String accountNumber, String currency) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.currency = currency;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.active = true;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
        this.lastUpdated = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
        this.lastUpdated = LocalDateTime.now();
    }

    public void close() {
        this.active = false;
        this.lastUpdated = LocalDateTime.now();
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/model/Transfer.java ===
package com.bank.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @NotNull(message = "Transfer ID cannot be null")
    private UUID id;

    @NotNull(message = "Source account ID cannot be null")
    private UUID sourceAccountId;

    @NotNull(message = "Target account ID cannot be null")
    private UUID targetAccountId;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Currency cannot be null")
    private String currency;

    @NotNull(message = "Transfer date cannot be null")
    private LocalDateTime transferDate;

    private String reference;
    private TransferStatus status;

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String currency, String reference) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency;
        this.transferDate = LocalDateTime.now();
        this.reference = reference;
        this.status = TransferStatus.PENDING;
    }

    public enum TransferStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REJECTED
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/ports/AccountRepository.java ===
package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Account;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(UUID customerId);

    void deleteById(UUID id);

    boolean existsByAccountNumber(String accountNumber);
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/ports/AuditService.java ===
package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Transfer;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Puerto de auditoría que define el contrato para registrar operaciones.
 * Esta interfaz vive en la capa de dominio y es implementada por la capa de infraestructura.
 */
public interface AuditService {

    /**
     * Registra una consulta de saldo de cuenta.
     * @param accountId Identificador único de la cuenta
     * @param accountNumber Número de cuenta consultada
     */
    void logAccountQuery(UUID accountId, String accountNumber);

    /**
     * Registra el inicio de una transferencia.
     * @param transfer Objeto Transfer con los detalles de la operación
     */
    void logTransferInitiated(Transfer transfer);

    /**
     * Registra la finalización exitosa de una transferencia.
     * @param transfer Objeto Transfer con los detalles de la operación completada
     */
    void logTransferCompleted(Transfer transfer);

    /**
     * Registra una transferencia fallida.
     * @param transfer Objeto Transfer con los detalles de la operación fallida
     * @param reason Razón del fallo
     */
    void logTransferFailed(Transfer transfer, String reason);

    /**
     * Registra una operación genérica en el sistema.
     * @param operation Nombre de la operación realizada
     * @param entityId Identificador de la entidad afectada
     * @param details Detalles adicionales de la operación
     */
    void logOperation(String operation, UUID entityId, String details);

    /**
     * Registra una operación con marca de tiempo específica.
     * @param operation Nombre de la operación
     * @param entityId Identificador de la entidad
     * @param details Detalles adicionales
     * @param timestamp Marca de tiempo de la operación
     */
    void logOperationWithTimestamp(String operation, UUID entityId, String details, LocalDateTime timestamp);

    /**
     * Registra un evento de validación de cuenta.
     * @param accountId Identificador de la cuenta
     * @param isValid Resultado de la validación
     * @param reason Razón si la validación falló
     */
    void logAccountValidation(UUID accountId, boolean isValid, String reason);

    /**
     * Registra una operación de depósito.
     * @param accountId Identificador de la cuenta
     * @param amount Monto depositado
     * @param newBalance Nuevo saldo de la cuenta
     */
    void logDeposit(UUID accountId, java.math.BigDecimal amount, java.math.BigDecimal newBalance);

    /**
     * Registra una operación de retiro.
     * @param accountId Identificador de la cuenta
     * @param amount Monto retirado
     * @param newBalance Nuevo saldo de la cuenta
     */
    void logWithdrawal(UUID accountId, java.math.BigDecimal amount, java.math.BigDecimal newBalance);
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/ports/TransferService.java ===
package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Transfer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de transferencia que define el contrato para operaciones de transferencia entre cuentas.
 * Esta interfaz vive en la capa de dominio y es implementada por la capa de aplicación.
 */
public interface TransferService {

    /**
     * Ejecuta una transferencia entre dos cuentas.
     * @param sourceAccountId ID de la cuenta de origen
     * @param targetAccountId ID de la cuenta de destino
     * @param amount Monto a transferir
     * @param reference Referencia única de la transferencia para idempotencia
     * @return Objeto Transfer con el resultado de la operación
     * @throws InsufficientBalanceException si el saldo es insuficiente
     */
    Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String reference);

    /**
     * Valida que una cuenta tenga saldo suficiente para una operación.
     * @param accountId ID de la cuenta a validar
     * @param amount Monto requerido
     * @return true si el saldo es suficiente, false en caso contrario
     */
    boolean validateBalance(UUID accountId, BigDecimal amount);

    /**
     * Busca una transferencia por su identificador único.
     * @param transferId ID de la transferencia
     * @return Optional con la transferencia si existe
     */
    Optional<Transfer> findById(UUID transferId);

    /**
     * Busca transferencias por ID de cuenta de origen.
     * @param accountId ID de la cuenta de origen
     * @return Lista de transferencias realizadas desde esa cuenta
     */
    List<Transfer> findBySourceAccountId(UUID accountId);

    /**
     * Busca transferencias por ID de cuenta de destino.
     * @param accountId ID de la cuenta de destino
     * @return Lista de transferencias recibidas en esa cuenta
     */
    List<Transfer> findByTargetAccountId(UUID accountId);

    /**
     * Busca una transferencia por su referencia (para idempotencia).
     * @param reference Referencia única de la transferencia
     * @return Optional con la transferencia si existe
     */
    Optional<Transfer> findByReference(String reference);

    /**
     * Cancela una transferencia pendiente.
     * @param transferId ID de la transferencia a cancelar
     * @return Transfer actualizada con estado CANCELLED
     */
    Transfer cancelTransfer(UUID transferId);

    /**
     * Obtiene el historial de transferencias de una cuenta (origen y destino).
     * @param accountId ID de la cuenta
     * @return Lista combinada de transferencias
     */
    List<Transfer> findAllByAccountId(UUID accountId);

    /**
     * Valida que una transferencia pueda realizarse según las reglas de negocio.
     * @param sourceAccountId ID de cuenta de origen
     * @param targetAccountId ID de cuenta de destino
     * @param amount Monto a transferir
     * @return true si la transferencia es válida
     */
    boolean validateTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount);
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/exception/InsufficientBalanceException.java ===
package com.bank.digital.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Excepción personalizada que se lanza cuando una cuenta no tiene saldo suficiente
 * para realizar una operación de retiro o transferencia.
 * Esta excepción pertenece a la capa de dominio y representa una violación de las
 * reglas de negocio del sistema bancario.
 */
public class InsufficientBalanceException extends RuntimeException {

    private final UUID accountId;
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final String accountNumber;

    /**
     * Constructor principal con todos los detalles de la excepción.
     * @param accountId Identificador único de la cuenta
     * @param accountNumber Número de cuenta legible
     * @param currentBalance Saldo actual de la cuenta
     * @param requestedAmount Monto solicitado para la operación
     */
    public InsufficientBalanceException(UUID accountId, String accountNumber, 
                                         BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(buildMessage(accountId, accountNumber, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    /**
     * Constructor alternativo que deduce el número de cuenta.
     * @param accountId Identificador único de la cuenta
     * @param currentBalance Saldo actual
     * @param requestedAmount Monto solicitado
     */
    public InsufficientBalanceException(UUID accountId, BigDecimal currentBalance, 
                                         BigDecimal requestedAmount) {
        super(buildMessage(accountId, null, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = null;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    /**
     * Construye el mensaje de error descriptivo.
     */
    private static String buildMessage(UUID accountId, String accountNumber, 
                                        BigDecimal currentBalance, BigDecimal requestedAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Saldo insuficiente para realizar la operación. ");
        sb.append("Cuenta: ");
        sb.append(accountNumber != null ? accountNumber : accountId.toString());
        sb.append(" | Saldo actual: ");
        sb.append(currentBalance != null ? currentBalance.toPlainString() : "0.00");
        sb.append(" | Monto solicitado: ");
        sb.append(requestedAmount != null ? requestedAmount.toPlainString() : "0.00");
        sb.append(" | Diferencia: ");
        if (currentBalance != null && requestedAmount != null) {
            sb.append(currentBalance.subtract(requestedAmount).toPlainString());
        } else {
            sb.append("0.00");
        }
        return sb.toString();
    }

    /**
     * Obtiene el identificador de la cuenta.
     * @return UUID de la cuenta
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Obtiene el número de cuenta.
     * @return Número de cuenta o null si no está disponible
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     * @return Saldo actual
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Obtiene el monto solicitado para la operación.
     * @return Monto solicitado
     */
    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Calcula la diferencia entre el saldo actual y el monto solicitado.
     * @return Diferencia (puede ser negativa)
     */
    public BigDecimal getDeficit() {
        if (currentBalance == null || requestedAmount == null) {
            return BigDecimal.ZERO;
        }
        return currentBalance.subtract(requestedAmount);
    }

    /**
     * Verifica si el déficit es crítico (más del 50% del saldo solicitado).
     * @return true si el déficit es crítico
     */
    public boolean isCriticalDeficit() {
        if (currentBalance == null || requestedAmount == null || requestedAmount.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        BigDecimal deficit = requestedAmount.subtract(currentBalance);
        return deficit.divide(requestedAmount, 4, java.math.RoundingMode.HALF_UP)
                      .compareTo(new BigDecimal("0.5")) > 0;
    }

    @Override
    public String toString() {
        return "InsufficientBalanceException{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", requestedAmount=" + requestedAmount +
                ", deficit=" + getDeficit() +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/domain/exception/CoreBankingException.java ===
package com.bank.digital.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para fallos del core bancario.
 * Se lanza cuando el servicio de core bancario no responde o retorna errores.
 */
public class CoreBankingException extends RuntimeException {
    
    private final String errorCode;
    private final UUID correlationId;
    private final LocalDateTime timestamp;
    private final String serviceName;
    private final String operation;
    
    public CoreBankingException(String message) {
        super(message);
        this.errorCode = "CORE_BANKING_ERROR";
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = "UNKNOWN";
    }
    
    public CoreBankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = "UNKNOWN";
    }
    
    public CoreBankingException(String message, Throwable cause, String operation) {
        super(message, cause);
        this.errorCode = "CORE_BANKING_ERROR";
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = operation;
    }
    
    public CoreBankingException(String message, Throwable cause, String errorCode, String operation) {
        super(message, cause);
        this.errorCode = errorCode;
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = operation;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public UUID getCorrelationId() {
        return correlationId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getOperation() {
        return operation;
    }
    
    @Override
    public String toString() {
        return String.format(
            "CoreBankingException{errorCode='%s', correlationId=%s, timestamp=%s, serviceName='%s', operation='%s', message='%s'}",
            errorCode, correlationId, timestamp, serviceName, operation, getMessage()
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/infrastructure/persistence/JpaAccountRepository.java ===
package com.bank.digital.infrastructure.persistence;

import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.ports.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del repositorio de cuentas utilizando Spring Data JPA.
 * Persiste y recupera cuentas desde la base de datos PostgreSQL.
 */
@Repository
@Transactional
public class JpaAccountRepository implements AccountRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(JpaAccountRepository.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(UUID.randomUUID());
            account.setCreatedAt(LocalDateTime.now());
            entityManager.persist(account);
            logger.info("Cuenta creada exitosamente: {} para cliente: {}", 
                account.getAccountNumber(), account.getCustomerId());
        } else {
            account.setLastUpdated(LocalDateTime.now());
            account = entityManager.merge(account);
            logger.info("Cuenta actualizada exitosamente: {}", account.getAccountNumber());
        }
        return account;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        Account account = entityManager.find(Account.class, id);
        return Optional.ofNullable(account);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.active = true",
            Account.class
        );
        query.setParameter("accountNumber", accountNumber);
        
        try {
            Account account = (Account) query.getSingleResult();
            return Optional.of(account);
        } catch (jakarta.persistence.NoResultException e) {
            logger.warn("No se encontró cuenta activa con número: {}", accountNumber);
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Account> findByCustomerId(UUID customerId) {
        logger.debug("Buscando cuentas para cliente: {}", customerId);
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.customerId = :customerId AND a.active = true ORDER BY a.createdAt DESC",
            Account.class
        );
        query.setParameter("customerId", customerId);
        
        List<Account> accounts = query.getResultList();
        logger.info("Se encontraron {} cuentas activas para el cliente: {}", accounts.size(), customerId);
        return accounts;
    }
    
    @Override
    public void deleteById(UUID id) {
        logger.info("Marcando cuenta como inactiva: {}", id);
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            account.setActive(false);
            account.setLastUpdated(LocalDateTime.now());
            entityManager.merge(account);
            logger.info("Cuenta marcada como inactiva: {}", id);
        } else {
            logger.warn("No se encontró cuenta para eliminar: {}", id);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByAccountNumber(String accountNumber) {
        logger.debug("Verificando existencia de cuenta: {}", accountNumber);
        Query query = entityManager.createQuery(
            "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber AND a.active = true",
            Long.class
        );
        query.setParameter("accountNumber", accountNumber);
        
        Long count = (Long) query.getSingleResult();
        boolean exists = count > 0;
        logger.debug("La cuenta {} existe: {}", accountNumber, exists);
        return exists;
    }
    
    /**
     * Actualiza el saldo de una cuenta de forma atómica.
     * Utiliza una consulta de actualización para garantizar consistencia.
     *
     * @param accountId ID de la cuenta
     * @param newBalance nuevo saldo
     * @return número de filas afectadas
     */
    @Transactional
    public int updateBalance(UUID accountId, BigDecimal newBalance) {
        logger.info("Actualizando saldo de cuenta {} a {}", accountId, newBalance);
        Query query = entityManager.createQuery(
            "UPDATE Account a SET a.balance = :balance, a.lastUpdated = :lastUpdated " +
            "WHERE a.id = :id AND a.active = true"
        );
        query.setParameter("balance", newBalance);
        query.setParameter("lastUpdated", LocalDateTime.now());
        query.setParameter("id", accountId);
        
        int updated = query.executeUpdate();
        logger.info("Filas actualizadas: {}", updated);
        return updated;
    }
    
    /**
     * Realiza una operación atómica de débito en la cuenta.
     * Verifica que el saldo sea suficiente antes de debitar.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a debitar
     * @return true si la operación fue exitosa, false si no hay saldo suficiente
     */
    @Transactional
    public boolean debitAccount(UUID accountId, BigDecimal amount) {
        logger.info("Debitando {} de la cuenta {}", amount, accountId);
        
        Account account = entityManager.find(Account.class, accountId);
        if (account == null || !account.isActive()) {
            logger.error("Cuenta no encontrada o inactiva: {}", accountId);
            return false;
        }
        
        if (account.getBalance().compareTo(amount) < 0) {
            logger.warn("Saldo insuficiente para cuenta {}: saldo={}, solicitado={}", 
                accountId, account.getBalance(), amount);
            return false;
        }
        
        account.withdraw(amount);
        account.setLastUpdated(LocalDateTime.now());
        entityManager.merge(account);
        
        logger.info("Débito exitoso. Nuevo saldo: {}", account.getBalance());
        return true;
    }
    
    /**
     * Realiza una operación atómica de crédito en la cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a acreditar
     * @return true si la operación fue exitosa
     */
    @Transactional
    public boolean creditAccount(UUID accountId, BigDecimal amount) {
        logger.info("Acreditando {} a la cuenta {}", amount, accountId);
        
        Account account = entityManager.find(Account.class, accountId);
        if (account == null || !account.isActive()) {
            logger.error("Cuenta no encontrada o inactiva: {}", accountId);
            return false;
        }
        
        account.deposit(amount);
        account.setLastUpdated(LocalDateTime.now());
        entityManager.merge(account);
        
        logger.info("Crédito exitoso. Nuevo saldo: {}", account.getBalance());
        return true;
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/application/AccountService.java ===
package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.ports.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Caso de uso para consultar saldos y gestionar cuentas.
 * Maneja la lógica de negocio relacionada con cuentas bancarias.
 */
@Service
public class AccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = BigDecimal.ZERO;
    private static final int MAX_ACCOUNTS_PER_CUSTOMER = 20;
    
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    /**
     * Consulta el saldo de una cuenta por su ID.
     *
     * @param accountId ID de la cuenta
     * @return el saldo de la cuenta
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalanceById(UUID accountId) {
        logger.info("Consultando saldo para cuenta: {}", accountId);
        
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            logger.error("Cuenta no encontrada: {}", accountId);
            throw new CoreBankingException(
                "Cuenta no encontrada: " + accountId,
                "ACCOUNT_NOT_FOUND",
                "GET_BALANCE"
            );
        }
        
        Account account = accountOpt.get();
        if (!account.isActive()) {
            logger.warn("Cuenta inactiva: {}", accountId);
            throw new CoreBankingException(
                "La cuenta está inactiva: " + accountId,
                "ACCOUNT_INACTIVE",
                "GET_BALANCE"
            );
        }
        
        logger.info("Saldo consultado exitosamente para cuenta {}: {}", 
            accountId, account.getBalance());
        return account.getBalance();
    }
    
    /**
     * Consulta el saldo de una cuenta por su número.
     *
     * @param accountNumber número de cuenta
     * @return el saldo de la cuenta
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalanceByAccountNumber(String accountNumber) {
        logger.info("Consultando saldo para número de cuenta: {}", accountNumber);
        
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty()) {
            logger.error("Cuenta no encontrada con número: {}", accountNumber);
            throw new CoreBankingException(
                "Cuenta no encontrada: " + accountNumber,
                "ACCOUNT_NOT_FOUND",
                "GET_BALANCE_BY_NUMBER"
            );
        }
        
        Account account = accountOpt.get();
        logger.info("Saldo consultado para cuenta {}: {}", 
            accountNumber, account.getBalance());
        return account.getBalance();
    }
    
    /**
     * Obtiene los datos completos de una cuenta por su ID.
     *
     * @param accountId ID de la cuenta
     * @return la cuenta encontrada
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public Account getAccountById(UUID accountId) {
        logger.info("Obteniendo datos de cuenta: {}", accountId);
        
        return accountRepository.findById(accountId)
            .orElseThrow(() -> {
                logger.error("Cuenta no encontrada: {}", accountId);
                return new CoreBankingException(
                    "Cuenta no encontrada: " + accountId,
                    "ACCOUNT_NOT_FOUND",
                    "GET_ACCOUNT"
                );
            });
    }
    
    /**
     * Lista todas las cuentas activas de un cliente.
     *
     * @param customerId ID del cliente
     * @return lista de cuentas activas
     */
    @Transactional(readOnly = true)
    public List<Account> getAccountsByCustomerId(UUID customerId) {
        logger.info("Listando cuentas para cliente: {}", customerId);
        
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        
        logger.info("Se encontraron {} cuentas para el cliente: {}", 
            accounts.size(), customerId);
        return accounts;
    }
    
    /**
     * Crea una nueva cuenta bancaria.
     *
     * @param customerId ID del cliente propietario
     * @param accountNumber número de cuenta único
     * @param currency moneda de la cuenta
     * @param initialBalance saldo inicial
     * @return la cuenta creada
     * @throws CoreBankingException si el número de cuenta ya existe
     */
    @Transactional
    public Account createAccount(UUID customerId, String accountNumber, 
                                  String currency, BigDecimal initialBalance) {
        logger.info("Creando cuenta para cliente {} con número: {}", customerId, accountNumber);
        
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            logger.error("El número de cuenta ya existe: {}", accountNumber);
            throw new CoreBankingException(
                "El número de cuenta ya existe: " + accountNumber,
                "DUPLICATE_ACCOUNT_NUMBER",
                "CREATE_ACCOUNT"
            );
        }
        
        List<Account> existingAccounts = accountRepository.findByCustomerId(customerId);
        if (existingAccounts.size() >= MAX_ACCOUNTS_PER_CUSTOMER) {
            logger.error("El cliente {} alcanzó el máximo de cuentas permitidas", customerId);
            throw new CoreBankingException(
                "El cliente ha alcanzado el máximo de cuentas permitidas",
                "MAX_ACCOUNTS_REACHED",
                "CREATE_ACCOUNT"
            );
        }
        
        Account account = new Account(customerId, accountNumber, currency);
        if (initialBalance != null && initialBalance.compareTo(MINIMUM_BALANCE) > 0) {
            account.deposit(initialBalance);
        }
        
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getId());
        return savedAccount;
    }
    
    /**
     * Realiza un depósito en una cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a depositar
     * @return la cuenta actualizada
     * @throws CoreBankingException si la cuenta no existe o el monto es inválido
     */
    @Transactional
    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta: {}", amount, accountId);
        
        validatePositiveAmount(amount, "DEPOSIT");
        
        Account account = getAccountById(accountId);
        account.deposit(amount);
        
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Realiza un retiro de una cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a retirar
     * @return la cuenta actualizada
     * @throws CoreBankingException si la cuenta no existe, está inactiva o saldo insuficiente
     */
    @Transactional
    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta: {}", amount, accountId);
        
        validatePositiveAmount(amount, "WITHDRAW");
        
        Account account = getAccountById(accountId);
        
        if (!account.isActive()) {
            logger.error("No se puede retirar de cuenta inactiva: {}", accountId);
            throw new CoreBankingException(
                "La cuenta está inactiva",
                "ACCOUNT_INACTIVE",
                "WITHDRAW"
            );
        }
        
        if (account.getBalance().compareTo(amount) < 0) {
            logger.error("Saldo insuficiente para retiro en cuenta: {}", accountId);
            throw new CoreBankingException(
                "Saldo insuficiente para realizar el retiro",
                "INSUFFICIENT_BALANCE",
                "WITHDRAW"
            );
        }
        
        account.withdraw(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * CIerra (desactiva) una cuenta bancaria.
     *
     * @param accountId ID de la cuenta a cerrar
     * @throws CoreBankingException si la cuenta no existe o tiene saldo
     */
    @Transactional
    public void closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);
        
        Account account = getAccountById(accountId);
        
        if (account.getBalance().compareTo(MINIMUM_BALANCE) > 0) {
            logger.error("No se puede cerrar cuenta con saldo: {}", account.getBalance());
            throw new CoreBankingException(
                "No se puede cerrar una cuenta con saldo pendiente",
                "ACCOUNT_HAS_BALANCE",
                "CLOSE_ACCOUNT"
            );
        }
        
        account.close();
        accountRepository.save(account);
        logger.info("Cuenta cerrada exitosamente: {}", accountId);
    }
    
    /**
     * Valida que el monto sea positivo.
     */
    private void validatePositiveAmount(BigDecimal amount, String operation) {
        if (amount == null || amount.compareTo(MINIMUM_BALANCE) <= 0) {
            logger.error("Monto inválido para operación {}: {}", operation, amount);
            throw new CoreBankingException(
                "El monto debe ser mayor a cero",
                "INVALID_AMOUNT",
                operation
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/application/TransferServiceImpl.java ===
package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.domain.ports.AccountRepository;
import com.bank.digital.domain.ports.AuditService;
import com.bank.digital.domain.ports.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);
    private static final BigDecimal MINIMUM_TRANSFER_AMOUNT = new BigDecimal("0.01");
    private static final int MAX_IDEMPOTENCY_KEYS = 10000;

    private final AccountRepository accountRepository;
    private final AuditService auditService;
    private final java.util.Map<String, Transfer> idempotencyCache = new java.util.ConcurrentHashMap<>();

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, AuditService auditService) {
        this.accountRepository = accountRepository;
        this.auditService = auditService;
    }

    @Override
    @Transactional
    public Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, 
                                     String currency, String reference, String idempotencyKey) {
        logger.info("Iniciando transferencia: {} -> {} por {} {}", sourceAccountId, targetAccountId, amount, currency);

        if (idempotencyKey != null && idempotencyCache.containsKey(idempotencyKey)) {
            logger.info("Transferencia idempotente detectada, retornando resultado cacheado para key: {}", idempotencyKey);
            return idempotencyCache.get(idempotencyKey);
        }

        validateTransferParameters(sourceAccountId, targetAccountId, amount, currency);

        Optional<Account> sourceAccountOpt = accountRepository.findById(sourceAccountId);
        Optional<Account> targetAccountOpt = accountRepository.findById(targetAccountId);

        if (sourceAccountOpt.isEmpty()) {
            logger.error("Cuenta de origen no encontrada: {}", sourceAccountId);
            throw new CoreBankingException("Cuenta de origen no encontrada: " + sourceAccountId);
        }

        if (targetAccountOpt.isEmpty()) {
            logger.error("Cuenta de destino no encontrada: {}", targetAccountId);
            throw new CoreBankingException("Cuenta de destino no encontrada: " + targetAccountId);
        }

        Account sourceAccount = sourceAccountOpt.get();
        Account targetAccount = targetAccountOpt.get();

        validateBalance(sourceAccount, amount);

        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, amount, currency, 
                reference != null ? reference : UUID.randomUUID().toString());
        transfer.setStatus(TransferStatus.COMPLETED);

        if (idempotencyKey != null) {
            if (idempotencyCache.size() >= MAX_IDEMPOTENCY_KEYS) {
                logger.warn("Cache de idempotencia lleno, limpiando entradas antiguas");
                idempotencyCache.clear();
            }
            idempotencyCache.put(idempotencyKey, transfer);
        }

        auditService.logOperation("TRANSFER", transfer.getId().toString(), 
                "Transferencia completada: " + sourceAccountId + " -> " + targetAccountId + " amount: " + amount);

        logger.info("Transferencia completada exitosamente: {}", transfer.getId());
        return transfer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transfer> getTransferHistory(UUID accountId) {
        logger.info("Consultando historial de transferencias para cuenta: {}", accountId);
        return List.of();
    }

    private void validateTransferParameters(UUID sourceAccountId, UUID targetAccountId, 
                                             BigDecimal amount, String currency) {
        if (sourceAccountId == null) {
            throw new CoreBankingException("El ID de cuenta de origen es requerido");
        }
        if (targetAccountId == null) {
            throw new CoreBankingException("El ID de cuenta de destino es requerido");
        }
        if (sourceAccountId.equals(targetAccountId)) {
            throw new CoreBankingException("No se puede transferir a la misma cuenta");
        }
        if (amount == null || amount.compareTo(MINIMUM_TRANSFER_AMOUNT) < 0) {
            throw new CoreBankingException("El monto de transferencia debe ser mayor a " + MINIMUM_TRANSFER_AMOUNT);
        }
        if (currency == null || currency.isBlank()) {
            throw new CoreBankingException("La moneda es requerida");
        }
    }

    private void validateBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            logger.warn("Saldo insuficiente para cuenta: {}. Saldo actual: {}, monto solicitado: {}", 
                    account.getAccountNumber(), account.getBalance(), amount);
            throw new InsufficientBalanceException("Saldo insuficiente. Saldo actual: " + account.getBalance() + 
                    ", monto solicitado: " + amount);
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/infrastructure/adapters/AccountController.java ===
package com.bank.digital.infrastructure.adapters;

import com.bank.digital.application.AccountService;
import com.bank.digital.application.TransferServiceImpl;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Cuentas", description = "Operaciones de consulta de saldo y gestión de cuentas")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final TransferServiceImpl transferService;

    @Autowired
    public AccountController(AccountService accountService, TransferServiceImpl transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @GetMapping("/{accountId}/balance")
    @Operation(summary = "Consultar saldo de cuenta", 
               description = "Retorna el saldo actual de una cuenta específica")
    public ResponseEntity<BalanceResponse> getBalance(
            @Parameter(description = "ID de la cuenta", required = true) 
            @PathVariable UUID accountId) {
        logger.info("Solicitud de saldo para cuenta: {}", accountId);
        long startTime = System.currentTimeMillis();

        try {
            Account account = accountService.getAccountById(accountId);
            BalanceResponse response = new BalanceResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getCurrency(),
                    account.isActive()
            );

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Consulta de saldo completada en {}ms para cuenta: {}", duration, accountId);

            if (duration > 200) {
                logger.warn("Latencia de consulta de saldo excedida: {}ms > 200ms", duration);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al consultar saldo para cuenta {}: {}", accountId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BalanceResponse(null, null, null, null, false));
        }
    }

    @PostMapping("/transfer")
    @Operation(summary = "Realizar transferencia", 
               description = "Ejecuta una transferencia entre dos cuentas")
    public ResponseEntity<TransferResponse> executeTransfer(@RequestBody TransferRequest request) {
        logger.info("Solicitud de transferencia: {} -> {} por {}", 
                request.sourceAccountId(), request.targetAccountId(), request.amount());
        long startTime = System.currentTimeMillis();

        try {
            Transfer transfer = transferService.executeTransfer(
                    request.sourceAccountId(),
                    request.targetAccountId(),
                    request.amount(),
                    request.currency(),
                    request.reference(),
                    request.idempotencyKey()
            );

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Transferencia completada en {}ms: {}", duration, transfer.getId());

            if (duration > 500) {
                logger.warn("Latencia de transferencia excedida: {}ms > 500ms", duration);
            }

            TransferResponse response = new TransferResponse(
                    transfer.getId(),
                    transfer.getSourceAccountId(),
                    transfer.getTargetAccountId(),
                    transfer.getAmount(),
                    transfer.getCurrency(),
                    transfer.getStatus().name(),
                    transfer.getTransferDate()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error al ejecutar transferencia: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TransferResponse(null, null, null, null, null, "FAILED", null));
        }
    }

    @GetMapping("/{accountId}/history")
    @Operation(summary = "Historial de transferencias", 
               description = "Retorna el historial de transferencias de una cuenta")
    public ResponseEntity<List<TransferResponse>> getTransferHistory(
            @Parameter(description = "ID de la cuenta", required = true) 
            @PathVariable UUID accountId) {
        logger.info("Solicitando historial de transferencias para cuenta: {}", accountId);

        try {
            List<Transfer> transfers = transferService.getTransferHistory(accountId);
            List<TransferResponse> response = transfers.stream()
                    .map(t -> new TransferResponse(
                            t.getId(),
                            t.getSourceAccountId(),
                            t.getTargetAccountId(),
                            t.getAmount(),
                            t.getCurrency(),
                            t.getStatus().name(),
                            t.getTransferDate()))
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener historial de transferencias: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    public record BalanceResponse(UUID id, String accountNumber, BigDecimal balance, 
                                   String currency, boolean active) {}

    public record TransferResponse(UUID id, UUID sourceAccountId, UUID targetAccountId, 
                                    BigDecimal amount, String currency, String status, 
                                    java.time.LocalDateTime transferDate) {}

    public record TransferRequest(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, 
                                   String currency, String reference, String idempotencyKey) {}
}

// === ARCHIVO: src/main/java/com/bank/digital/infrastructure/audit/AuditServiceImpl.java ===
package com.bank.digital.infrastructure.audit;

import com.bank.digital.domain.ports.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void logOperation(String operationType, String entityId, String details) {
        logger.info("Registrando operación en auditoría: tipo={}, entidad={}", operationType, entityId);

        AuditEntry entry = new AuditEntry(
                UUID.randomUUID(),
                operationType,
                entityId,
                details,
                LocalDateTime.now(),
                "SYSTEM"
        );

        try {
            auditLogRepository.save(entry);
            logger.debug("Entrada de auditoría persistida: {}", entry.id());
        } catch (Exception e) {
            logger.error("Error al persistir entrada de auditoría: {}", e.getMessage());
            throw new RuntimeException("Error al registrar operación en auditoría", e);
        }
    }

    @Override
    public void logOperation(String operationType, String entityId, String details, String userId) {
        logger.info("Registrando operación en auditoría: tipo={}, entidad={}, usuario={}", 
                operationType, entityId, userId);

        AuditEntry entry = new AuditEntry(
                UUID.randomUUID(),
                operationType,
                entityId,
                details,
                LocalDateTime.now(),
                userId
        );

        try {
            auditLogRepository.save(entry);
            logger.debug("Entrada de auditoría persistida: {}", entry.id());
        } catch (Exception e) {
            logger.error("Error al persistir entrada de auditoría: {}", e.getMessage());
        }
    }

    public record AuditEntry(UUID id, String operationType, String entityId, 
                              String details, LocalDateTime timestamp, String userId) {}

    public interface AuditLogRepository {
        void save(AuditEntry entry);
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/infrastructure/config/ResilienceConfig.java ===
package com.bank.digital.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1))
                .build();
        return RetryRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/bank/digital/infrastructure/config/SwaggerConfig.java ===
package com.bank.digital.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Bank API")
                        .version("1.0.0")
                        .description("API REST para gestión de cuentas y transferencias bancarias")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@bankdigital.com")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Token JWT de autenticación")));
    }
}

// === ARCHIVO: src/test/java/com/bank/digital/application/TransferServiceImplTest.java ===
package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.domain.ports.AccountRepository;
import com.bank.digital.domain.ports.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransferServiceImpl - Pruebas Unitarias")
class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private TransferServiceImpl transferService;

    private Account sourceAccount;
    private Account targetAccount;
    private UUID sourceAccountId;
    private UUID targetAccountId;

    @BeforeEach
    void setUp() {
        sourceAccountId = UUID.randomUUID();
        targetAccountId = UUID.randomUUID();
        sourceAccount = new Account(sourceAccountId, "ACC001", "USD");
        sourceAccount.deposit(new BigDecimal("1000.00"));
        targetAccount = new Account(targetAccountId, "ACC002", "USD");
        targetAccount.deposit(new BigDecimal("500.00"));
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Transferencia exitosa entre dos cuentas")
        void transfer_Success_BetweenAccounts() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal amount = new BigDecimal("200.00");
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-001");

            assertNotNull(result);
            assertEquals(TransferStatus.COMPLETED, result.getStatus());
            assertEquals(amount, result.getAmount());
            assertEquals(sourceAccountId, result.getSourceAccountId());
            assertEquals(targetAccountId, result.getTargetAccountId());

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository, times(2)).save(accountCaptor.capture());

            Account savedSource = accountCaptor.getAllValues().get(0);
            Account savedTarget = accountCaptor.getAllValues().get(1);
            assertEquals(new BigDecimal("800.00"), savedSource.getBalance());
            assertEquals(new BigDecimal("700.00"), savedTarget.getBalance());

            verify(auditService).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Transferencia por monto exacto igual al saldo")
        void transfer_Success_ExactBalanceAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal amount = new BigDecimal("1000.00");
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-EXACT");

            assertNotNull(result);
            assertEquals(TransferStatus.COMPLETED, result.getStatus());
            assertEquals(BigDecimal.ZERO, sourceAccount.getBalance());

            verify(auditService).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Transferencia múltiples successful consecutivas")
        void transfer_Success_MultipleTransfers() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal firstAmount = new BigDecimal("100.00");
            transferService.transfer(sourceAccountId, targetAccountId, firstAmount, "USD", "REF-1");

            BigDecimal secondAmount = new BigDecimal("150.00");
            transferService.transfer(sourceAccountId, targetAccountId, secondAmount, "USD", "REF-2");

            assertEquals(new BigDecimal("750.00"), sourceAccount.getBalance());
            assertEquals(new BigDecimal("750.00"), targetAccount.getBalance());

            verify(auditService, times(2)).logTransfer(any(Transfer.class));
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Fallo por saldo insuficiente")
        void transfer_Failure_InsufficientBalance() {
            sourceAccount.withdraw(new BigDecimal("1000.00"));
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(InsufficientBalanceException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-FAIL");
            });

            verify(accountRepository, never()).save(any(Account.class));
            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por cuenta de origen inexistente")
        void transfer_Failure_SourceAccountNotFound() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.empty());
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(CoreBankingException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-NO-SOURCE");
            });

            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por cuenta de destino inexistente")
        void transfer_Failure_TargetAccountNotFound() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.empty());

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(CoreBankingException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-NO-TARGET");
            });

            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por monto negativo")
        void transfer_Failure_NegativeAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal negativeAmount = new BigDecimal("-50.00");

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, negativeAmount, "USD", "REF-NEG");
            });
        }

        @Test
        @DisplayName("Fallo por monto cero")
        void transfer_Failure_ZeroAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal zeroAmount = BigDecimal.ZERO;

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, zeroAmount, "USD", "REF-ZERO");
            });
        }

        @Test
        @DisplayName("Fallo por moneda diferente")
        void transfer_Failure_CurrencyMismatch() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "EUR", "REF-CURRENCY");
            });
        }
    }

    @Nested
    @DisplayName("Validaciones de Comportamiento")
    class BehaviorValidations {

        @Test
        @DisplayName("La transferencia registra la fecha actual")
        void transfer_RecordsCurrentTimestamp() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            LocalDateTime beforeTransfer = LocalDateTime.now();
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("50.00"), "USD", "REF-TIME");
            LocalDateTime afterTransfer = LocalDateTime.now();

            assertNotNull(result.getTransferDate());
            assertTrue(result.getTransferDate().isEqual(beforeTransfer) || result.getTransferDate().isAfter(beforeTransfer));
            assertTrue(result.getTransferDate().isEqual(afterTransfer) || result.getTransferDate().isBefore(afterTransfer));
        }

        @Test
        @DisplayName("La transferencia genera un ID único")
        void transfer_GeneratesUniqueId() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Transfer result1 = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("10.00"), "USD", "REF-ID-1");
            Transfer result2 = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("10.00"), "USD", "REF-ID-2");

            assertNotEquals(result1.getId(), result2.getId());
        }

        @Test
        @DisplayName("La referencia se preserva en la transferencia")
        void transfer_PreservesReference() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            String reference = "TXN-2024-001234";
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("100.00"), "USD", reference);

            assertEquals(reference, result.getReference());
        }
    }
}

// === ARCHIVO: src/test/java/com/bank/digital/infrastructure/adapters/AccountControllerTest.java ===
package com.bank.digital.infrastructure.adapters;


import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.application.AccountService;
import com.bank.digital.application.TransferServiceImpl;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.infrastructure.config.SwaggerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@Import(SwaggerConfig.class)
@DisplayName("AccountController - Pruebas de Integración")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransferServiceImpl transferService;

    private Account testAccount;
    private UUID accountId;
    private String accountJson;
    private String transferJson;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        testAccount = new Account(accountId, "ACC-TEST-001", "USD");
        testAccount.deposit(new BigDecimal("1500.00"));

        accountJson = """
            {
                "customerId": "%s",
                "accountNumber": "ACC-TEST-002",
                "currency": "USD"
            }
            """.formatted(UUID.randomUUID());

        transferJson = """
            {
                "sourceAccountId": "%s",
                "targetAccountId": "%s",
                "amount": 250.00,
                "currency": "USD",
                "reference": "TEST-REF-001"
            }
            """.formatted(accountId, UUID.randomUUID());
    }

    @Nested
    @DisplayName("GET /api/accounts/{id} - Consultar Cuenta")
    class GetAccountTests {

        @Test
        @DisplayName("Consulta exitosa de cuenta por ID")
        void getAccount_Success_ReturnsAccount() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(accountId.toString()))
                    .andExpect(jsonPath("$.accountNumber").value("ACC-TEST-001"))
                    .andExpect(jsonPath("$.balance").value(1500.00))
                    .andExpect(jsonPath("$.currency").value("USD"))
                    .andExpect(jsonPath("$.active").value(true));
        }

        @Test
        @DisplayName("Consulta falla cuando la cuenta no existe")
        void getAccount_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(get("/api/accounts/{id}", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/accounts/{id}/balance - Consultar Saldo")
    class GetBalanceTests {

        @Test
        @DisplayName("Consulta exitosa de saldo")
        void getBalance_Success_ReturnsBalance() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}/balance", accountId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.accountId").value(accountId.toString()))
                    .andExpect(jsonPath("$.balance").value(1500.00))
                    .andExpect(jsonPath("$.currency").value("USD"));
        }

        @Test
        @DisplayName("Consulta de saldo falla para cuenta inexistente")
        void getBalance_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(get("/api/accounts/{id}/balance", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/accounts - Crear Cuenta")
    class CreateAccountTests {

        @Test
        @DisplayName("Creación exitosa de cuenta")
        void createAccount_Success_ReturnsCreated() throws Exception {
            when(accountService.createAccount(any(Account.class))).thenReturn(testAccount);

            mockMvc.perform(post("/api/accounts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(accountJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.accountNumber").value("ACC-TEST-001"))
                    .andExpect(jsonPath("$.balance").value(1500.00));
        }

        @Test
        @DisplayName("Creación falla con datos inválidos")
        void createAccount_InvalidData_Returns400() throws Exception {
            String invalidJson = """
                {
                    "accountNumber": "",
                    "currency": ""
                }
                """;

            mockMvc.perform(post("/api/accounts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(invalidJson))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/transfers - Realizar Transferencia")
    class TransferTests {

        @Test
        @DisplayName("Transferencia exitosa")
        void transfer_Success_ReturnsAccepted() throws Exception {
            UUID targetAccountId = UUID.randomUUID();
            Transfer successfulTransfer = new Transfer(
                    this.accountId,
                    targetAccountId,
                    new BigDecimal("250.00"),
                    "USD",
                    "TEST-REF-001"
            );

            when(transferService.transfer(eq(this.accountId), eq(targetAccountId), any(), eq("USD"), eq("TEST-REF-001")))
                    .thenReturn(successfulTransfer);

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 250.00,
                    "currency": "USD",
                    "reference": "TEST-REF-001"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isAccepted())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.sourceAccountId").value(this.accountId.toString()))
                    .andExpect(jsonPath("$.targetAccountId").value(targetAccountId.toString()))
                    .andExpect(jsonPath("$.amount").value(250.00))
                    .andExpect(jsonPath("$.status").value("COMPLETED"));
        }

        @Test
        @DisplayName("Transferencia falla por saldo insuficiente")
        void transfer_InsufficientBalance_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            when(transferService.transfer(eq(this.accountId), eq(targetAccountId), any(), eq("USD"), any()))
                    .thenThrow(new com.bank.digital.domain.exception.InsufficientBalanceException("Saldo insuficiente"));

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 5000.00,
                    "currency": "USD",
                    "reference": "TEST-REF-FAIL"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("Transferencia falla con monto inválido")
        void transfer_InvalidAmount_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": -100.00,
                    "currency": "USD",
                    "reference": "TEST-REF-INVALID"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Transferencia falla sin referencia")
        void transfer_MissingReference_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 100.00,
                    "currency": "USD"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /api/accounts/{id} - Eliminar Cuenta")
    class DeleteAccountTests {

        @Test
        @DisplayName("Eliminación exitosa de cuenta")
        void deleteAccount_Success_ReturnsNoContent() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(delete("/api/accounts/{id}", accountId))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Eliminación falla para cuenta inexistente")
        void deleteAccount_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(delete("/api/accounts/{id}", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Validaciones de Formato JSON")
    class JsonFormatTests {

        @Test
        @DisplayName("Respuesta con campos nulos no incluye claves nulas")
        void response_ExcludesNullFields() throws Exception {
            Account accountWithNulls = new Account(accountId, "ACC-NULL", "USD");
            when(accountService.getAccountById(accountId)).thenReturn(accountWithNulls);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(accountId.toString()))
                    .andExpect(jsonPath("$.balance").doesNotExist());
        }

        @Test
        @DisplayName("Content-Type correcto en respuestas")
        void response_HasCorrectContentType() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }
}
```
