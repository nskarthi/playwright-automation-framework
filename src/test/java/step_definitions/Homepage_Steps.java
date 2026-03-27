package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.internal.annotations.BaseAnnotation;
import pages.HomePage;
import pages.base.BasePage;

import java.awt.*;

public class Homepage_Steps {

    public BrowserManager browserManager;
    private final HomePage homePage;

    public Homepage_Steps(BrowserManager browserManager, HomePage homePage) {
        this.browserManager = browserManager;
        this.homePage = homePage;
    }

    @Given("I navigate to the webdriveruniversity homepage")
    public void i_navigate_to_the_webdriveruniversity_homepage() {
        System.out.println("landing in homepage");
        homePage.navigateToHomePage();
    }

    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        System.out.println("clicking contact us");
        homePage.clickContactUsLink();
    }

    @When("I click on the LOGIN PORTAL link")
    public void i_click_on_the_login_portal_link() {
        System.out.println("clicking login portal link");
        homePage.clickLoginPortalLink();
    }
}
