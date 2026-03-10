package com.budgetapp.qa.steps;

import com.budgetapp.qa.pages.DashboardPage;
import com.budgetapp.qa.pages.TransactionPage;
import net.serenitybdd.annotations.Step;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionSteps {

    DashboardPage dashboardPage;
    TransactionPage transactionPage;

    @Step("User registers income: {0} for amount {1}")
    public void registerIncome(String description, String amount) {
        dashboardPage.clickTransactionsMenu();
        transactionPage.clickNewTransaction();
        transactionPage.selectIncomeType();
        transactionPage.enterDescription(description);
        transactionPage.enterAmount(amount);
        transactionPage.clickCreateTransaction();
    }

    @Step("User verifies income is reflected in the main panel report")
    public void verifyIncomeIsReflected() {
        assertTrue(dashboardPage.isReportsHeadingVisible());
        assertFalse(dashboardPage.getTotalIncomeText().isEmpty());
    }
}
