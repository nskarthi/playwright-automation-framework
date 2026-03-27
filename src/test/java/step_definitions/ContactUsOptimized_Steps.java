package step_definitions;

import browser.BrowserManager;
import context.PersonContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import org.testng.Assert;
import pages.ContactUsPage;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactUsOptimized_Steps {
    public BrowserManager browserManager;
    String submitSelector = "input[value='SUBMIT']";
    String thankYouSelector = "#contact_reply h1";
    String errorSelector = "body";
    public static final String FIELDS_REQUIRED_ERROR = "Error: all fields are required";
    public static final String INVALID_EMAIL_ERROR = "Error: Invalid email address";
    Faker faker = new Faker();
    private final PersonContext personContext;
    private final ContactUsPage contactUsPage;

    public ContactUsOptimized_Steps(BrowserManager browserManager, PersonContext personContext, ContactUsPage contactUsPage) {
        this.browserManager = browserManager;
        this.personContext = personContext;
        this.contactUsPage = contactUsPage;
    }

    private static final Map<String, String> fieldMap = new HashMap<>() {{
        put("first name", "First Name");
        put("last name", "Last Name");
        put("email", "Email Address");
        put("comment", "Comments");
        put("comments", "Comments"); // plural alias
    }};

    @When("I enter {string} as {string}")
    public void i_enter_as(String inputValue, String fieldName) {
        if (inputValue != null && !inputValue.trim().isEmpty()) {
            //String selector = mapFieldToName(fieldName);
            //browserManager.getPage().getByPlaceholder(selector).fill(inputValue);
            contactUsPage.enterData(fieldName, inputValue);
        } else {
            System.out.println("Skipping field: " + fieldName + " (value was empty)");
        }
    }

    @And("I type {string} into the {string} field")
    public void i_type_into_the_field(String inputValue, String fieldName) {
        if (inputValue != null && !inputValue.trim().isEmpty()) {
            //String selector = mapFieldToName(fieldName);
            //browserManager.getPage().getByPlaceholder(selector).fill(inputValue);
            contactUsPage.enterData(fieldName, inputValue);
        } else {
            System.out.println("Skipping field: " + fieldName + " (value was empty)");
        }
    }

    @Then("I should see the error {string}")
    public void i_should_see_the_error(String expectedError) {
        String text = contactUsPage.getUnsuccessfulSubmissionMessage();
        Assert.assertTrue(text.contains(expectedError));
    }

    private String mapFieldToName(String fieldName) {
        String selector = fieldMap.get(fieldName.toLowerCase());
        if (selector == null) {
            throw new RuntimeException("No selector found for field: " + fieldName);
        }
        return selector;
    }

    @And("I type a random first name")
    public void i_type_a_random_firstName() {
        String selector = mapFieldToName("first name");
        String inputValue = faker.name().firstName();
        personContext.setRandomFirstname(inputValue);
        System.out.println("Random firstname: " + inputValue);
        contactUsPage.enterData(selector, inputValue);
    }

    @And("I type a random last name")
    public void i_type_a_random_lastName() {
        String selector = mapFieldToName("last name");
        String inputValue = faker.name().lastName();
        personContext.setRandomLastname(inputValue);
        System.out.println("Random lastname: " + inputValue);
        contactUsPage.enterData(selector, inputValue);
    }

    @And("I type a random email")
    public void i_type_a_random_email() {
        //String selector = mapFieldToName("email");
        String inputValue = faker.internet().emailAddress();
        personContext.setRandomEmail(inputValue);
        System.out.println("Random email: " + inputValue);
        contactUsPage.enterData("email", inputValue);
    }

    @And("I type a random comment")
    public void i_type_a_random_comment() {
        String selector = mapFieldToName("comment");
        String inputValue = faker.strangerThings().quote();
        String comment = "How can I help you " + personContext.getRandomFirstname() + " " + personContext.getRandomLastname()
                + " [ " + personContext.getRandomEmail() + " ]\n" + inputValue;
        System.out.println(comment);
        contactUsPage.enterData(selector, inputValue);
    }
}
