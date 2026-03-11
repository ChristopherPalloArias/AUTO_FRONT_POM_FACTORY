package com.budgetapp.qa.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;

@DefaultUrl("page:webdriver.base.url")
public class LoginPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//a[contains(.,'Regístrate aquí')]")
    private WebElementFacade registerLink;

    public void enterEmail(String email) {
        emailInput.waitUntilVisible().type(email);
    }

    public void enterPassword(String password) {
        passwordInput.type(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickRegisterLink() {
        registerLink.waitUntilClickable().click();
    }
}
