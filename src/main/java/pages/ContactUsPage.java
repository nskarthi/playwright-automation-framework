package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.base.BasePage;

import java.util.HashMap;
import java.util.Map;

public class ContactUsPage extends BasePage {
    String submitSelector = "input[value='SUBMIT']";
    String thankYouSelector = "#contact_reply h1";
    String errorSelector = "body";

    private static final Map<String, String> fieldMap = new HashMap<>() {{
        put("first name", "First Name");
        put("last name", "Last Name");
        put("email", "Email Address");
        put("comment", "Comments");
        put("comments", "Comments"); // plural alias
    }};

    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeFirstName(String firstName) {
        fillData("First Name", firstName);
    }

    public void typeLastName(String lastName) {
        fillData("Last Name", lastName);
    }

    public void typeEmailAddress(String emailAddress) {
        fillData("Email Address", emailAddress);
    }

    public void typeComments(String comments) {
        fillData("Comments", comments);
    }

    public void enterData(String fieldName, String fieldValue){
        String selector = mapFieldToName(fieldName);
        fillData(selector, fieldValue);
    }

    public void clickSubmitButton() {
        waitAndClickBySelector(submitSelector);
    }

    public String getSuccessfulSubmissionMessage() {
        Locator locator= getBrowserManager().getPage().locator(thankYouSelector);
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return locator.textContent();
    }

    public String getUnsuccessfulSubmissionMessage() {
        Locator locator= getBrowserManager().getPage().locator(errorSelector);
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return locator.textContent();
    }

    private String mapFieldToName(String fieldName) {
        String selector = fieldMap.get(fieldName.toLowerCase());
        if (selector == null) {
            throw new RuntimeException("No selector found for field: " + fieldName);
        }
        return selector;
    }
}
