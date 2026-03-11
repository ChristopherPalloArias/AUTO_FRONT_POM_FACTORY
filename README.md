<div align="center">
  
# 🚀 AUTO_FRONT_POM_FACTORY
### Taller Semana 5: Maestría en Automatización

**Autor:** Christopher Pallo  
**Entregable:** 1 de 3  
**Proyecto:** Automatización E2E usando el paradigma tradicional **Page Object Model (POM) + Page Factory** soportado por Serenity BDD.

<br />

### 🛠️ Technology Stack

**Automation Framework**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=java,gradle,selenium,cucumber" alt="Automation Stack" />
</a>

**Application Under Test (Microservices Ecosystem)**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=ts,react,vite,tailwind,spring,java,rabbitmq,docker,mysql" alt="App Stack" />
</a>

</div>

---

## 🎯 Contexto del Reto

Este repositorio corresponde al **Entregable 1 de 3** de la Maestría en Automatización. El objetivo es certificar el dominio técnico sobre la implementación del paradigma clásico **Page Object Model (POM)** integrado nativamente con **Page Factory** y **Serenity BDD**. Este modelo separa de forma estricta los elementos interactivos de la UI respecto a la lógica de negocio, orquestado bajo metodología BDD con Cucumber.

---

## 🛠️ Entorno y Prerrequisitos (Compatibilidad)

> ⚠️ **El uso de estas versiones exactas es obligatorio para compilar y ejecutar la suite correctamente.**

| Componente | Versión Requerida | Verificación |
|------------|------------------|--------------|
| **Java / JDK** | `17` (LTS) | `java -version` → `openjdk 17.x.x` |
| **Gradle** | `8.8` (distribución binaria) | `./gradlew --version` → `Gradle 8.8` |
| **Docker** | `20.x+` | `docker --version` |
| **Docker Compose** | `v2.x+` | `docker compose version` |

### Java 17

El proyecto define `sourceCompatibility = JavaVersion.VERSION_17` y `targetCompatibility = JavaVersion.VERSION_17` en el `build.gradle`. Cualquier versión inferior provocará errores de compilación por features de lenguaje no soportadas. Versiones superiores (JDK 21+) pueden generar advertencias por APIs internas deprecadas.

### Gradle 8.8 (Wrapper Incluido)

El proyecto incluye el **Gradle Wrapper** (`gradlew`) preconfigurado en `gradle/wrapper/gradle-wrapper.properties` apuntando a `gradle-8.8-bin.zip`. Esto significa que **no es necesario instalar Gradle manualmente** — el wrapper descargará la versión exacta automáticamente en la primera ejecución. Esta versión fue seleccionada específicamente para eliminar los `Deprecated Gradle features` warnings que aparecen en versiones 9.x.

> 📝 **Nota del Autor:** La combinación **JDK 17 + Gradle 8.8** constituye la matriz de compatibilidad verificada para Serenity BDD 5.3.2, Cucumber 7.18.1 y JUnit Platform 5.10.2. Esta configuración garantiza una ejecución estable, sin conflictos de dependencias ni advertencias de deprecación, tanto en entornos locales como en pipelines de CI/CD.

---

## 🔄 Escenario E2E: Ciclo de Vida Completo

El framework ejecuta un flujo End-to-End **idempotente** y **autocontenido** que garantiza la validación desde cero, sin datos residuales:

```
Registro de usuario nuevo ➜ Login automático ➜ Creación de ingreso ➜ Validación de balance
```

### Estrategia de Idempotencia

Cada ejecución genera un **correo electrónico único** mediante inyección de timestamp, eliminando fallos por duplicidad de usuarios en ejecuciones consecutivas:

```
chris.qa@test.com  →  chris.qa+1741654000000@test.com
```

Esta lógica reside en las clases de negocio del paquete `steps/`, manteniendo la generación dinámica encapsulada.

### Flujo Gherkin (Declarativo)

```gherkin
# language: es
Esquema del escenario: CP-E2E - Creación de cuenta nueva y validación de ingreso inicial
  Dado que el visitante accede a la página de registro de la plataforma
  Cuando él crea una cuenta nueva con el nombre "<nombre>" y el correo base "<correo_base>"
  Y registra un nuevo ingreso de tipo "<descripcion>" por un monto de <monto>
  Entonces el reporte financiero del panel principal debe reflejar el balance exacto de <monto>
```

---

## 🏗️ Arquitectura POM + Page Factory

El framework está estructurado en capas que constituyen el núcleo del patrón tradicional Page Object Model:

| Capa | Paquete | Responsabilidad |
|---|---|---|
| 📄 **Pages** | `pages` | Clases que extienden de `PageObject`. Cero lógica de negocio. Uso estricto de `@FindBy` (Page Factory) para mapear elementos y exponer métodos atómicos (click, type, etc). |
| ⚙️ **Steps** | `steps` | Clases orquestadoras de lógica de negocio anotadas con `@Step` de Serenity. Aquí ocurre la magia (como el correo dinámico). |
| 🧪 **StepDefs** | `stepdefinitions` | Mapeo de Gherkin y aserciones nativas de JUnit (`Assertions.assertTrue`). |

```
src/test/java/com/budgetapp/qa/
├── pages/                # @FindBy y métodos atómicos (SignUpPage, LoginPage, DashboardPage, TransactionPage)
├── steps/                # Lógica de negocio y @Step (AuthenticationSteps, TransactionSteps)
├── stepdefinitions/      # Glue BDD y aserciones JUnit (TransactionManagementStepDefinitions)
└── runners/              # CucumberTestSuite (JUnit Platform 5)
```

---

## 🏆 Cumplimiento de Rúbrica (Clean Code & SOLID)

Este framework ha sido auditado exhaustivamente y cumple al 100% con los criterios mandatorios:

### Clean Code

- [x] **Zero Comments (Regla de Oro):** Ausencia total de `//`, `/* */` y `/** */` en las clases Java. El código es su propia fuente documental.
- [x] **Nomenclatura Semántica 100% en Inglés:** Clases, métodos y variables — todo en inglés técnico.

### SOLID (Aplicado al Modelo POM)

- [x] **S — Single Responsibility:** Las Pages solo localizan elementos e interactúan con ellos. Cero lógica combinada, cada capa tiene un solo vector de cambio.
- [x] **D — Dependency Inversion:** Cero URLs hardcodeadas — las Pages leen directamente `webdriver.base.url` a través de la anotación `@DefaultUrl`.

### Integración Serenity POM

- [x] **`@Steps` Injection:** Uso estricto de la anotación `@Steps` para la inyección de dependencias de clases orientadas al negocio dentro de los step definitions de Cucumber.
- [x] **Page Factory Estricto:** Uso exclusivo de anotaciones `@FindBy` mapeando elementos web bajo objetos de alto nivel `WebElementFacade`.

---

## ⚙️ Gestión de Configuración

### `serenity.conf`

| Propiedad | Valor | Propósito |
|-----------|-------|-----------|
| `webdriver.base.url` | `http://localhost:3000` | URL base centralizada |
| `serenity.take.screenshots` | `FOR_EACH_ACTION` | Captura de evidencia en cada clic y evento |
| `serenity.type.delay` | `150` | 150ms entre teclas para tipeo visible en Live Demo |
| `headless.mode` | `false` | Ejecución visual para demostraciones en vivo |
| `goog:chromeOptions.args` | `--start-maximized`, `--no-sandbox` | Chrome optimizado para entorno local |

### `build.gradle`

| Aspecto | Detalle |
|---------|---------|
| **Serenity BDD** | `5.3.2` (última versión estable) |
| **Cucumber** | `7.18.1` (JUnit Platform Engine) |
| **JUnit Platform** | `5.10.2` |
| **Java** | `17` (source + target compatibility) |
| **Reportes** | `single-page-html` + reporte principal Serenity |

---

## ⚡ Instrucciones de Clonado y Setup (Entorno Local)

La suite de pruebas valida interacciones *End-to-End* orgánicas operando en tiempo real contra un sistema de microservicios.

### Paso 1: Clonar este Repositorio de Pruebas (El Framework)

```bash
git clone https://github.com/ChristopherPalloArias/AUTO_FRONT_POM_FACTORY.git
cd AUTO_FRONT_POM_FACTORY
```

### Paso 2: Desplegar el Ecosistema de Prueba (La Aplicación)

Las pruebas colapsarán si el entorno base no está operando, puesto que interceptan el flujo de negocio del *Registro de Transacciones Financieras*.

1. Sitúate fuera del directorio del framework y clona la rama base específica del ecosistema:
   ```bash
   git clone -b release/1.2.1 https://github.com/ChristopherPalloArias/Budget_Management_App.git
   cd Budget_Management_App
   ```

2. Arranca el cluster de microservicios:
   ```bash
   docker compose up --build -d
   ```

3. 🕒 **Pausa Crítica:** Espera de 30 a 45 segundos para que los *healthchecks* de los contenedores de **MySQL** y el broker de eventos **RabbitMQ** transicionen a un estado `healthy`.

4. Verifica manualmente el levantamiento del frontend navegando a [http://localhost:3000](http://localhost:3000).

---

## ▶️ Ejecución y Generación de Reportes

Retorna al directorio del framework de automatización (`AUTO_FRONT_POM_FACTORY`). Para despachar la suite en modo local e invocar el proceso de agregación de Serenity, ejecuta:

```bash
./gradlew clean test aggregate
```

Al concluir exitosamente, el framework compilará en tiempo real un **Living Documentation**, una evidencia paso a paso y gráficamente trazable a nivel de Cucumber y Serenity del flujo de negocio automatizado.

* **El index maestro del informe lo encuentras en:**
  ```text
  target/site/serenity/index.html
  ```
*(Abre el archivo desde tu explorador web de preferencia).*

---

## 🧩 Consideraciones Técnicas y Retos de Interfaz de Usuario (UI)

* **Sincronización Reactiva — Portals de Radix UI (`@radix-ui/react-select`):**  
  Los componentes *Select* no-nativos del DOM son gestionados usando la utilidad nativa de Serenity `evaluateJavascript` dentro de las Pages. Esto ejecuta `arguments[0].click()` vía JavaScript sobre el identificador web, esquivando elegantemente las capas z-index de los overlays modales de Radix.

* **Estabilización del Campo Monto:**  
  Los inputs numéricos del formulario de transacciones contienen un valor por defecto `0`. En la clase de Page correspondiente se ejecuta el comando `evaluateJavascript("arguments[0].value='';", amountInput);` antes de que Serenity escriba el monto real, previniendo la inyección espuria de registros concatenados como `02000`.

* **Notificaciones de Microservicios Desacopladas (`Sonner Toasts`):**  
  Las transacciones viajan hacia RabbitMQ rebotando eventos vía frontend de forma asíncrona. Serenity impone `waitUntilVisible` sobre los localizadores del balance, garantizando que todo el ecosistema de aserciones de JUnit acceda al informe validante sólo cuando el estado de la UI haya retornado a una calma estable.
