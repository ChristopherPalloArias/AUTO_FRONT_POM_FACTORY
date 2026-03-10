# AUTO_FRONT_POM_FACTORY
Automatización End-to-End (E2E) de la aplicación de Gestión de Presupuesto utilizando Serenity BDD, Cucumber y el patrón Page Object Model (POM) con Page Factory.

## Requisitos Previos
- Java 17 o superior.
- Gradle 9.x.
- Google Chrome instalado.
- La aplicación frontend debe estar ejecutándose localmente en http://localhost:3000.

## Ejecución de las Pruebas
Para ejecutar los escenarios y generar el reporte de Serenity, utiliza el siguiente comando:

```bash
./gradlew clean test aggregate
```

El reporte interactivo se generará en la ruta: `target/site/serenity/index.html`.
