package com.budgetapp.qa.steps;

import com.budgetapp.qa.pages.DashboardPage;
import com.budgetapp.qa.pages.TransactionPage;
import net.serenitybdd.annotations.Step;

import java.text.NumberFormat;
import java.util.Locale;

public class TransactionSteps {

    private DashboardPage dashboardPage;
    private TransactionPage transactionPage;

    @Step("Navigate to transactions list")
    public void navigateToTransactions() {
        dashboardPage.navigateToTransactions();
    }

    @Step("Register a new income with description '{0}' and amount '{1}'")
    public void registerNewIncome(String description, int amount) {
        transactionPage.clickNewTransaction();
        transactionPage.selectTypeIncome();
        transactionPage.selectCategorySalary();
        transactionPage.enterDescription(description);
        transactionPage.enterAmount(String.valueOf(amount));
        transactionPage.clickCreateTransaction();
    }

    @Step("Navigate back to dashboard")
    public void navigateToDashboard() {
        dashboardPage.navigateToDashboard();
    }

    @Step("Get formatted balance from dashboard report")
    public String getFormattedBalanceAmount() {
        dashboardPage.clickGenerateReports();
        return dashboardPage.getBalanceValue();
    }

    @Step("Format expected amount into Colombian currency")
    public String formatExpectedAmount(int expectedAmount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("es", "CO"));
        return formatter.format(expectedAmount);
    }
}
