package com.budgetapp.qa.stepdefinitions;

import com.budgetapp.qa.steps.AuthenticationSteps;
import com.budgetapp.qa.steps.TransactionSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Steps;

public class TransactionManagementStepDefinitions {

    @Steps
    AuthenticationSteps authSteps;

    @Steps
    TransactionSteps txSteps;

    @Dado("que el usuario inicia sesion exitosamente en la aplicacion")
    public void theUserSuccessfullyLogsIntoTheApplication() {
        authSteps.loginAs("admin@budget.com", "password123");
        authSteps.verifyLoginSuccess();
    }

    @Cuando("el registra un nuevo ingreso por {string} con un valor de {int}")
    public void theUserRegistersANewIncomeEntry(String description, int amount) {
        txSteps.registerIncome(description, String.valueOf(amount));
    }

    @Entonces("el debe ver el ingreso reflejado en el reporte del panel principal")
    public void theUserShouldSeeTheIncomeReflectedInTheDashboardReport() {
        txSteps.verifyIncomeIsReflected();
    }
}
