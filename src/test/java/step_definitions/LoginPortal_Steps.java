package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class LoginPortal_Steps {
    public BrowserManager browserManager;
    String alertText;

    public LoginPortal_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @And("I type a username {string}")
    public void i_type_as_username(String inputValue) {
        System.out.println("Entering Username: " + inputValue);
        browserManager.getPage().getByPlaceholder("Username").fill(inputValue);
    }

    @And("I type a password {string}")
    public void i_type_as_password(String inputValue) {
        System.out.println("Entering Password: " + inputValue);
        browserManager.getPage().getByPlaceholder("Password").fill(inputValue);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() throws InterruptedException {
        System.out.println("Clicking Login button");
        browserManager.getPage().onceDialog(dialog -> {
            alertText = dialog.message();
            System.out.println(alertText);
            dialog.accept();
        });

        Locator loginButton = browserManager.getPage().locator("#login-button");
        loginButton.hover();
        loginButton.click(new Locator.ClickOptions().setForce(true));
    }

    @Then("I should be presented with an alert box with the message {string}")
    public void i_should_be_presented_with_a_validation_succeeded_message(String expectedAlertText) {
        System.out.println("message pop-up");
        assertEquals(expectedAlertText, alertText);
    }
}
