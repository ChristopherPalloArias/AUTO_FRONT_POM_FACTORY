<div align="center">
  
# 🚀 AUTO_FRONT_POM_FACTORY
### Taller Semana 5: Maestría en Automatización

**Autor:** Christopher Pallo  
**Proyecto:** Automatización End-to-End (E2E) robusta centrada en arquitectura limpia y mantenibilidad operativa a nivel de framework.

<br />

### 🛠️ Technology Stack

**Automation Framework**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=java,gradle,selenium,cucumber" alt="Automation Stack" />
</a>

**Application Under Test (Microservices Ecosytem)**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=ts,react,vite,tailwind,spring,java,rabbitmq,docker,mysql" alt="App Stack" />
</a>

</div>

---

## 🎯 Contexto del Reto

Este repositorio corresponde al **Entregable 1 de 3** de la Maestría en Automatización, el cual tiene como objetivo certificar el máximo dominio técnico sobre la implementación del patrón arquitectónico **Page Object Model (POM) con Page Factory** de Selenium, aplicando de forma estricta las convenciones de *Clean Code* bajo metodologías BDD. Todo encapsulado y orquestado bajo **Serenity BDD**.

---

## 🏆 Reglas de Oro del Proyecto (Criterios de Evaluación)

Este framework ha sido auditado y cumple al 100% con los siguientes criterios mandatorios de la semana 5:

- [x] **POM + Page Factory:** Implementación pura e inyección de dependencias web gestionada exclusivamente con la anotación `@FindBy`, sin inicialización de constructores intrusivos de `PageFactory.initElements()`, apalancado por el ciclo de vida del `PageObject` de Serenity.
- [x] **Zero Comments (Clean Code CRÍTICO):** Ausencia total, estricta e intransigente de cualquier tipo de código comentado, línea `//` explicativa o *Javadoc* interno en las clases `.java`. El código es su propia fuente documental.
- [x] **Nomenclatura Semántica:** Patrón `camelCase` riguroso, nombrando métodos auto-descriptivos, declarativos de acciones, selectores y variables de negocio, documentados iterativamente 100% en inglés.
- [x] **Arquitectura y Aislamiento:** Gestión modular y limpia de todo el classpath del proyecto mediante `build.gradle` y orquestación dinámica del `webdriver` a través del `serenity.conf`.

---

## ⚡ Instrucciones de Clonado y Setup (Entorno Local)

La suite de pruebas valida interacciones *End-to-End* orgánicas operando en tiempo real contra un sistema de microservicios.

### Paso 1: Clonar este Repositorio de Pruebas (El Framework)

Clona este repositorio donde residen los scripts E2E que auditarán el sistema:

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

2. Arranca el cluster de microservicios en caché desacoplado:
   ```bash
   docker compose up --build -d
   ```

3. 🕒 **Pausa Crítica:** Espera de 30 a 45 segundos para que los *healthchecks* de los contenedores de **MySQL** y el broker de eventos **RabbitMQ** transicionen a un estado `healthy`.

4. Verifica manualmente el levantamiento del front end navegando a [http://localhost:3000](http://localhost:3000).

---

## ▶️ Ejecución y Generación de Reportes

Retorna al directorio del framework de automatización (`AUTO_FRONT_POM_FACTORY`). Para despachar la suite en modo local e invocar el proceso de agregación de Serenity, ejecuta:

```bash
./gradlew clean test aggregate
```

Al concluir exitosamente, el framework compilará en tiempo real un **Living Documentation**, una evidencia paso a paso y gráficamente trazable a nivel de Cucumber y Selenium del flujo de negocio automatizado.

* **El index maestro del informe lo encuentras en:**
  ```text
  target/site/serenity/index.html
  ```
*(Abre el archivo desde tu explorador web de preferencia).*

---

## 🧩 Consideraciones Técnicas y Retos de Interfaz de Usuario (UI)

* **Sincronización Reactiva - Portals de Radix UI (`@radix-ui/react-select`):** 
  El motor de tests gestiona componentes *Select* no-nativos del DOM. Para sortear las intercepciones físicas provocadas por la animación de salida (*exit animations* o *Portal overlays*) de las cajas de diálogo modales (`div[@role='option']`), se implementaron:
  * Localizadores XPath indexados `(//button[@role='combobox'])[n]`.
  * **JavaScript Clicks (`evaluateJavascript`)** focalizados al trigger directo del DOM para eludir las capas z-index de la barrera de intercepción visual de Radix.
* **Notificaciones de Microservicios Desacopladas (`Sonner Toasts`):**
  Las transacciones viajan hacia RabbitMQ rebotando eventos vía frontend asíncronamente; se introdujeron esperas explícitas idiomáticas sobre locators paramétricos como `[data-sonner-toast]` y pausas visuales de estabilización (`waitABit(1500)`) que no comprometen la encapsulación delegada al Patrón de la Página *(PageObject)*.
