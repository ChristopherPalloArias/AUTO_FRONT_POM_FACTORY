package com.budgetapp.qa.steps;

import com.budgetapp.qa.pages.LoginPage;
import net.serenitybdd.annotations.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationSteps {

    LoginPage loginPage;

    @Step("User logs in as {0}")
    public void loginAs(String email, String password) {
        loginPage.open();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Step("User verifies login was successful")
    public void verifyLoginSuccess() {
        assertTrue(loginPage.isSuccessToastVisible());
    }
}
