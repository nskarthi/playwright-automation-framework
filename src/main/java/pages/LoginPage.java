package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    private final String loginButton = "#login-button";

    public LoginPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeUserName(String fieldName, String fieldValue) {
        fillData(fieldName, fieldValue);
    }

    public void typePassword(String fieldName, String fieldValue) {
        fillData(fieldName, fieldValue);
    }

    public String clickLoginButtonAndGetAlertText() {
        final String[] alertText = {""};
        getBrowserManager().getPage().onceDialog(dialog -> {
            alertText[0] = dialog.message();
            System.out.println(alertText[0]);
            dialog.accept();
        });

        Locator button = getBrowserManager().getPage().locator(loginButton);
        button.hover();
        waitAndClickByLocator(button);
        System.out.println("yey, worked!");
        return alertText[0];
    }
}
