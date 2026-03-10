package com.budgetapp.qa.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject {

    @FindBy(xpath = "//h2[contains(text(),'Reportes Financieros')]")
    private WebElementFacade reportsHeading;

    @FindBy(xpath = "//p[normalize-space()='Ingresos del período']/preceding-sibling::div[contains(@class,'text-green')]")
    private WebElementFacade totalIncomeValue;

    @FindBy(xpath = "//a[contains(@href, '/transactions')]")
    private WebElementFacade transactionsMenuLink;

    public boolean isReportsHeadingVisible() {
        return reportsHeading.isVisible();
    }

    public String getTotalIncomeText() {
        return totalIncomeValue.getText();
    }

    public void clickTransactionsMenu() {
        transactionsMenuLink.click();
    }
}
