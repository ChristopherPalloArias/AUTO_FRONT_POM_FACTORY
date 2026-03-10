# language: es
Característica: Gestión de ingresos y reportes financieros
  Como usuario de la aplicación de presupuesto
  Quiero registrar mis ingresos
  Para visualizar el impacto en mi balance general y reportes

  Escenario: Registro exitoso de un nuevo ingreso salarial
    Dado que el usuario inicia sesion exitosamente en la aplicacion
    Cuando el registra un nuevo ingreso por "Salario Mensual" con un valor de 2000
    Entonces el debe ver el ingreso reflejado en el reporte del panel principal
