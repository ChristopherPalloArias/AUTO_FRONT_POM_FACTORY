package com.budgetapp.qa.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class TransactionPage extends PageObject {

    @FindBy(xpath = "//button[.//span[text()='Nueva Transacción']]")
    private WebElementFacade newTransactionButton;

    @FindBy(xpath = "//h2[text()='Nueva Transacción']")
    private WebElementFacade transactionModalTitle;

    @FindBy(xpath = "//button[@role='combobox'][ancestor::*[.//label[text()='Tipo']]]")
    private WebElementFacade typeSelectTrigger;

    @FindBy(xpath = "//div[@role='option'][.='Ingreso']")
    private WebElementFacade incomeOption;

    @FindBy(css = "input[placeholder='Ej: Supermercado']")
    private WebElementFacade descriptionInput;

    @FindBy(css = "input[type='number'][placeholder='0.00']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//button[@type='submit'][contains(.,'Crear Transacción')]")
    private WebElementFacade createTransactionButton;

    public void clickNewTransaction() {
        newTransactionButton.click();
        transactionModalTitle.waitUntilVisible();
    }

    public void selectIncomeType() {
        typeSelectTrigger.click();
        incomeOption.waitUntilVisible().click();
    }

    public void enterDescription(String description) {
        descriptionInput.type(description);
    }

    public void enterAmount(String amount) {
        amountInput.type(amount);
    }

    public void clickCreateTransaction() {
        createTransactionButton.click();
    }
}
