package com.budgetapp.qa.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class TransactionPage extends PageObject {

    @FindBy(xpath = "//button[.//span[text()='Nueva Transacción']]")
    private WebElementFacade newTransactionButton;

    @FindBy(xpath = "//h2[text()='Nueva Transacción']")
    private WebElementFacade transactionModalTitle;

    @FindBy(xpath = "(//div[@role='dialog']//button[@role='combobox'])[1]")
    private WebElementFacade typeSelectTrigger;

    @FindBy(xpath = "//div[@role='option'][normalize-space()='Ingreso']")
    private WebElementFacade incomeOption;

    @FindBy(xpath = "(//div[@role='dialog']//button[@role='combobox'])[2]")
    private WebElementFacade categorySelectTrigger;

    @FindBy(xpath = "//div[@role='option'][normalize-space()='Salario']")
    private WebElementFacade salaryCategoryOption;

    @FindBy(css = "input[placeholder='Ej: Supermercado']")
    private WebElementFacade descriptionInput;

    @FindBy(css = "input[type='number'][placeholder='0.00']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//button[@type='submit'][contains(.,'Crear Transacción')]")
    private WebElementFacade createTransactionButton;

    public void clickNewTransaction() {
        newTransactionButton.waitUntilVisible().click();
        transactionModalTitle.waitUntilVisible();
    }

    public void selectTypeIncome() {
        evaluateJavascript("arguments[0].click();", typeSelectTrigger);
        incomeOption.waitUntilVisible().click();
    }

    public void selectCategorySalary() {
        evaluateJavascript("arguments[0].click();", categorySelectTrigger);
        salaryCategoryOption.waitUntilVisible().click();
    }

    public void enterDescription(String description) {
        descriptionInput.type(description);
    }

    public void enterAmount(String amount) {
        evaluateJavascript("arguments[0].value='';", amountInput);
        amountInput.type(amount);
    }

    public void clickCreateTransaction() {
        createTransactionButton.click();
    }
}
