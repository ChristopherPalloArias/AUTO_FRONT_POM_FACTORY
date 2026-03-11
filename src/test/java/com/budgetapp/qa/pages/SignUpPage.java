package com.budgetapp.qa.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SignUpPage extends PageObject {

    @FindBy(id = "displayName")
    private WebElementFacade displayNameInput;

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElementFacade confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit'][contains(.,'Crear Cuenta')]")
    private WebElementFacade submitButton;

    public void enterDisplayName(String displayName) {
        displayNameInput.waitUntilVisible().type(displayName);
    }

    public void enterEmail(String email) {
        emailInput.type(email);
    }

    public void enterPassword(String password) {
        passwordInput.type(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.type(confirmPassword);
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
