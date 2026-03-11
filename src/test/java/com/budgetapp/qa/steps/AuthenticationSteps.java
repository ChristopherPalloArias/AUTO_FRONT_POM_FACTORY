package com.budgetapp.qa.steps;

import com.budgetapp.qa.pages.DashboardPage;
import com.budgetapp.qa.pages.LoginPage;
import com.budgetapp.qa.pages.SignUpPage;
import net.serenitybdd.annotations.Step;

public class AuthenticationSteps {

    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private DashboardPage dashboardPage;

    private static final String DEFAULT_PASSWORD = "Password123!";
    private String generatedEmail;

    @Step("Access the registration page via base URL")
    public void accessRegistrationPage() {
        loginPage.open();
        loginPage.clickRegisterLink();
    }

    @Step("Register a new user with name '{0}' and base email '{1}'")
    public void registerNewAccount(String displayName, String emailBase) {
        generatedEmail = emailBase.replace("@", "+" + System.currentTimeMillis() + "@");
        
        signUpPage.enterDisplayName(displayName);
        signUpPage.enterEmail(generatedEmail);
        signUpPage.enterPassword(DEFAULT_PASSWORD);
        signUpPage.enterConfirmPassword(DEFAULT_PASSWORD);
        signUpPage.clickSubmit();
    }

    @Step("Login with the newly generated account credentials")
    public void loginWithGeneratedCredentials() {
        loginPage.enterEmail(generatedEmail);
        loginPage.enterPassword(DEFAULT_PASSWORD);
        loginPage.clickSubmit();
    }

    @Step("Verify that login was successful and dashboard is loaded")
    public boolean isDashboardVisible() {
        return dashboardPage.isReportsHeadingVisible();
    }
}
