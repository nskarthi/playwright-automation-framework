package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactUs_Steps {
    public BrowserManager browserManager;
    String submitSelector = "input[value='SUBMIT']";
    String thankYouSelector = "#contact_reply h1";
    String errorSelector = "body";
    public static final String FIELDS_REQUIRED_ERROR = "Error: all fields are required";
    public static final String INVALID_EMAIL_ERROR = "Error: Invalid email address";

    public ContactUs_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @When("I type a first name")
    public void i_type_a_first_name() {
        System.out.println("firstname");
        browserManager.page.getByPlaceholder("First Name").fill("Sam");
    }

    @When("I type a last name")
    public void i_type_a_last_name() {
        System.out.println("lastname");
        browserManager.page.getByPlaceholder("Last Name").fill("Curran");
    }

    @When("I type an email address")
    public void i_type_an_email_address() {
        System.out.println("email");
        browserManager.page.getByPlaceholder("Email Address").fill("sam_curran@gmail.com");
    }

    @When("I type a comment")
    public void i_type_a_comment() {
        System.out.println("comments");
        browserManager.page.getByPlaceholder("Comments").fill("My comments");
    }

    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        System.out.println("submit");
        browserManager.page.waitForSelector(submitSelector, browserManager.options);
        browserManager.page.click(submitSelector);
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        System.out.println("thank you page");
        String thankYouMessage = "Thank You for your Message!";
        browserManager.page.waitForSelector(thankYouSelector, browserManager.options);
        String text = browserManager.page.waitForSelector(thankYouSelector).textContent();
        System.out.println("Thank you page text: " + text);
        assertThat(browserManager.page.locator(thankYouSelector)).hasText(thankYouMessage);
    }

    @Then("I should be presented with an error page for missing email address")
    public void i_should_be_presented_with_an_error_page_for_missing_email_address(DataTable dataTable) {
        System.out.println("unsuccessful submission error page");
        List<String> expectedErrors = dataTable.asList();
        Locator errorElement = browserManager.page.locator(errorSelector);
        String actualErrorMessage = errorElement.textContent();
        System.out.println("Error page text: " + errorElement.textContent());
        assertThat(errorElement).isVisible();
        assertThat(errorElement).containsText(FIELDS_REQUIRED_ERROR);
        assertThat(errorElement).containsText(INVALID_EMAIL_ERROR);

        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(actualErrorMessage);
        Assert.assertTrue(matcher.find(), "Actual error message does not match the expected error message. Found text " + actualErrorMessage);

        for (String error : expectedErrors)
            assertThat(errorElement).containsText(error);
    }

    @Then("I should be presented with an error page for missing {string}")
    public void i_should_be_presented_with_an_error_page_for_missing_first_name(String field, DataTable dataTable) {
        System.out.println("unsuccessful submission error page");
        List<String> expectedErrors = dataTable.asList();
        Locator errorElement = browserManager.page.locator(errorSelector);
        System.out.println("Error page text: " + errorElement.textContent());
        assertThat(errorElement).isVisible();
        assertThat(errorElement).containsText(FIELDS_REQUIRED_ERROR);

        for (String error : expectedErrors)
            assertThat(errorElement).containsText(error);
    }
}
