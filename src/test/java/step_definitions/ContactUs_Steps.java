package step_definitions;

import browser.BrowserManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ContactUsPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUs_Steps {
    public BrowserManager browserManager;
    public static final String FIELDS_REQUIRED_ERROR = "Error: all fields are required";
    public static final String INVALID_EMAIL_ERROR = "Error: Invalid email address";
    private final ContactUsPage contactUsPage;

    public ContactUs_Steps(BrowserManager browserManager, ContactUsPage contactUsPage) {
        this.browserManager = browserManager;
        this.contactUsPage = contactUsPage;
    }

    @When("I type a first name")
    public void i_type_a_first_name() {
        System.out.println("firstname");
        contactUsPage.typeFirstName("Sam");
    }

    @When("I type a last name")
    public void i_type_a_last_name() {
        System.out.println("lastname");
        contactUsPage.typeLastName("Curran");
    }

    @When("I type an email address")
    public void i_type_an_email_address() {
        System.out.println("email");
        contactUsPage.typeEmailAddress("sam_curran@gmail.com");
    }

    @When("I type a comment")
    public void i_type_a_comment() {
        System.out.println("comments");
        contactUsPage.typeComments("My comments");
    }

    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        System.out.println("submit");
        contactUsPage.clickSubmitButton();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        System.out.println("thank you page");
        String thankYouMessage = "Thank You for your Message!";
        String text = contactUsPage.getSuccessfulSubmissionMessage();
        System.out.println("Thank you page text: " + text);
        Assert.assertTrue(text.contains(thankYouMessage), "Mismatch in successful submission message. Actual: " +
                text + ", Expected: " + thankYouMessage);
    }

    @Then("I should be presented with an error page for missing email address")
    public void i_should_be_presented_with_an_error_page_for_missing_email_address(DataTable dataTable) {
        System.out.println("unsuccessful submission error page");
        List<String> expectedErrors = dataTable.asList();
        String actualErrorMessage = contactUsPage.getUnsuccessfulSubmissionMessage();
        System.out.println("Error page text: " + actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains(FIELDS_REQUIRED_ERROR));
        Assert.assertTrue(actualErrorMessage.contains(INVALID_EMAIL_ERROR));

        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(actualErrorMessage);
        Assert.assertTrue(matcher.find(), "Actual error message does not match the expected error message. Found text " + actualErrorMessage);

        for (String error : expectedErrors)
            Assert.assertTrue(actualErrorMessage.contains(error));
    }

    @Then("I should be presented with an error page for missing {string}")
    public void i_should_be_presented_with_an_error_page_for_missing_field(String field, DataTable dataTable) {
        System.out.println("unsuccessful submission error page");
        List<String> expectedErrors = dataTable.asList();
        String actualErrorMessage = contactUsPage.getUnsuccessfulSubmissionMessage();
        System.out.println("Error page text: " + actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains(FIELDS_REQUIRED_ERROR));

        for (String error : expectedErrors)
            Assert.assertTrue(actualErrorMessage.contains(error));
    }
}
