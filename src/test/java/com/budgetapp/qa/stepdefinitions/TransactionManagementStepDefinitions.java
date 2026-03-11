package com.budgetapp.qa.stepdefinitions;

import com.budgetapp.qa.steps.AuthenticationSteps;
import com.budgetapp.qa.steps.TransactionSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.annotations.Steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionManagementStepDefinitions {

    @Steps
    private AuthenticationSteps authenticationSteps;

    @Steps
    private TransactionSteps transactionSteps;

    @Dado("que el visitante accede a la página de registro de la plataforma")
    public void theVisitorAccessesTheRegistrationPage() {
        authenticationSteps.accessRegistrationPage();
    }

    @Cuando("él crea una cuenta nueva con el nombre {string} y el correo base {string}")
    public void heCreatesANewAccountWithNameAndEmail(String name, String emailBase) throws InterruptedException {
        authenticationSteps.registerNewAccount(name, emailBase);
        Thread.sleep(1000);
        authenticationSteps.loginWithGeneratedCredentials();
        assertTrue(authenticationSteps.isDashboardVisible());
    }

    @Y("registra un nuevo ingreso de tipo {string} por un monto de {int}")
    public void heRegistersANewIncomeEntry(String description, int amount) throws InterruptedException {
        transactionSteps.navigateToTransactions();
        transactionSteps.registerNewIncome(description, amount);
        Thread.sleep(2000);
    }

    @Entonces("el reporte financiero del panel principal debe reflejar el balance exacto de {int}")
    public void theFinancialReportShouldReflectTheExactBalance(int expectedAmount) throws InterruptedException {
        transactionSteps.navigateToDashboard();
        String actualBalanceText = transactionSteps.getFormattedBalanceAmount();
        String expectedFormattedAmount = transactionSteps.formatExpectedAmount(expectedAmount);
        
        assertTrue(actualBalanceText.contains(expectedFormattedAmount));
        Thread.sleep(1000);
    }
}
