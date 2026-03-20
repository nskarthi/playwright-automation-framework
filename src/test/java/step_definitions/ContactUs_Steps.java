package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUs_Steps {
    @When("I type a first name")
    public void i_type_a_first_name() {
        System.out.println("firstname");
    }
    @When("I type a last name")
    public void i_type_a_last_name() {
        System.out.println("lastname");
    }
    @When("I type an email address")
    public void i_type_an_email_address() {
        System.out.println("email");
    }
    @When("I type a comment")
    public void i_type_a_comment() {
        System.out.println("comments");
    }
    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        System.out.println("submit");
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        System.out.println("thank you page");
    }
}
