package com.budgetapp.qa.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class DashboardPage extends PageObject {

    @FindBy(xpath = "//h2[contains(text(),'Reportes Financieros')]")
    private WebElementFacade reportsHeading;

    @FindBy(xpath = "//div[text()='Balance Total']/parent::div/following-sibling::div/div")
    private WebElementFacade balanceValue;

    @FindBy(xpath = "//button[contains(.,'Consultar Reportes')]")
    private WebElementFacade generateReportsButton;

    @FindBy(xpath = "//a[contains(@href, '/transactions')]")
    private WebElementFacade transactionsMenuLink;

    @FindBy(xpath = "//a[contains(@href, '/dashboard')]")
    private WebElementFacade dashboardMenuLink;

    public boolean isReportsHeadingVisible() {
        return reportsHeading.waitUntilVisible().isVisible();
    }

    public void clickGenerateReports() {
        generateReportsButton.waitUntilVisible().click();
        balanceValue.waitUntilVisible();
    }

    public String getBalanceValue() {
        return balanceValue.waitUntilVisible().getText();
    }

    public void navigateToTransactions() {
        transactionsMenuLink.waitUntilClickable().click();
    }

    public void navigateToDashboard() {
        dashboardMenuLink.waitUntilClickable().click();
        reportsHeading.waitUntilVisible();
    }
}
