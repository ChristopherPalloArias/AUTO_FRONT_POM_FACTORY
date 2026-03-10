package com.budgetapp.qa.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;

    @FindBy(css = "[data-sonner-toast][data-type='success']")
    private WebElementFacade successToast;

    public void enterEmail(String email) {
        emailInput.type(email);
    }

    public void enterPassword(String password) {
        passwordInput.type(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isSuccessToastVisible() {
        return successToast.waitUntilVisible().isVisible();
    }
}
