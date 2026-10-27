# Implementación de un servicio REST en un dominio de banca

En el contexto de un banco digital, debes implementar un servicio REST que permita a los clientes consultar su saldo y realizar transferencias entre cuentas. El servicio debe enrutar las solicitudes, controlar variables de entorno, generar logs y conectarse a una base de datos utilizando un ORM. Los actores involucrados son el cliente, el core bancario y el sistema de auditoría. El servicio debe manejar idempotencia en las transferencias, validar que el saldo del cliente sea suficiente antes de realizar la transferencia y registrar cada operación en el sistema de auditoría. La latencia máxima permitida para las consultas de saldo es de 200ms y para las transferencias es de 500ms. En caso de fallo del core bancario, el servicio debe reintentar la operación hasta 3 veces con un intervalo de 1 segundo entre reintentos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del entorno y enrutamiento de solicitudes

**Objetivo:** Configurar el entorno de desarrollo y enrutar las solicitudes REST al servicio.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el entorno de desarrollo con las variables de entorno necesarias.
- Implementar el enrutamiento de solicitudes REST al servicio.

**Entregable:** Entorno de desarrollo configurado y solicitudes REST enrutadas al servicio.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la configuración del entorno de desarrollo.
- Piensa en cómo puedes hacer que el enrutamiento de solicitudes sea eficiente y escalable.

</details>

### Fase 2: Implementación de la lógica de negocio

**Objetivo:** Implementar la lógica de negocio para consultar el saldo y realizar transferencias.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar la lógica para consultar el saldo de una cuenta.
- Implementar la lógica para realizar transferencias entre cuentas, asegurando la idempotencia y validando el saldo suficiente.

**Entregable:** Lógica de negocio implementada para consultar saldos y realizar transferencias.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda que debes manejar la idempotencia en las transferencias.
- Piensa en cómo puedes validar que el saldo del cliente sea suficiente antes de realizar la transferencia.

</details>

### Fase 3: Generación de logs y conexión a la base de datos

**Objetivo:** Generar logs para cada operación y conectar el servicio a la base de datos utilizando un ORM.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Implementar la generación de logs para cada operación realizada por el servicio.
- Conectar el servicio a la base de datos utilizando un ORM.

**Entregable:** Logs generados para cada operación y conexión a la base de datos establecida.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en qué información es relevante incluir en los logs.
- Considera las mejores prácticas para conectar el servicio a la base de datos utilizando un ORM.

</details>

### Fase 4: Registro de operaciones en el sistema de auditoría

**Objetivo:** Registrar cada operación realizada por el servicio en el sistema de auditoría.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Implementar la lógica para registrar cada operación realizada por el servicio en el sistema de auditoría.

**Entregable:** Operaciones registradas en el sistema de auditoría.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo puedes asegurar que cada operación se registre correctamente en el sistema de auditoría.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un servicio REST y cómo se utiliza en el contexto de un banco digital?
- **paraQueSirve**: ¿Para qué sirve la idempotencia en las transferencias y cómo se implementa?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para conectar el servicio a la base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar la lógica de negocio para consultar saldos y realizar transferencias?
- **queDecisionesImplica**: ¿Qué decisiones implica la generación de logs y el registro de operaciones en el sistema de auditoría?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo y enrutamiento de solicitudes REST.
- Implementación correcta de la lógica de negocio para consultar saldos y realizar transferencias.
- Generación de logs relevantes para cada operación realizada por el servicio.
- Conexión correcta del servicio a la base de datos utilizando un ORM.
- Registro correcto de cada operación realizada por el servicio en el sistema de auditoría.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
