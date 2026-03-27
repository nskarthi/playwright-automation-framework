package step_definitions;

import browser.BrowserManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginPortal_Steps {
    public BrowserManager browserManager;
    String alertText;
    private final LoginPage loginPage;

    public LoginPortal_Steps(BrowserManager browserManager, LoginPage loginPage) {
        this.browserManager = browserManager;
        this.loginPage =loginPage;
    }

    @And("I type a username {string}")
    public void i_type_as_username(String inputValue) {
        System.out.println("Entering Username: " + inputValue);
        loginPage.typeUserName("Username", inputValue);
    }

    @And("I type a password {string}")
    public void i_type_as_password(String inputValue) {
        System.out.println("Entering Password: " + inputValue);
        loginPage.typePassword("Password", inputValue);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        System.out.println("Clicking Login button");
        alertText = loginPage.clickLoginButtonAndGetAlertText();
    }

    @Then("I should be presented with an alert box with the message {string}")
    public void i_should_be_presented_with_a_validation_succeeded_message(String expectedAlertText) {
        System.out.println("message pop-up");
        assertEquals(expectedAlertText, alertText);
    }
}
