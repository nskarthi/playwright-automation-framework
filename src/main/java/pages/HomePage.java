package pages;

import browser.BrowserManager;
import pages.base.BasePage;

public class HomePage extends BasePage  {

    public HomePage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void navigateToHomePage() {
        navigate("https://webdriveruniversity.com/");
    }

    public void clickContactUsLink() {
        getBrowserManager().setPage(getBrowserManager().getBrowserContext().waitForPage(() -> {
            waitAndClickByRole("LINK", "CONTACT US Contact Us Form");
        }));
        getBrowserManager().getPage().bringToFront();
    }

    public void clickLoginPortalLink() {
        getBrowserManager().setPage(getBrowserManager().getBrowserContext().waitForPage(() -> {
            waitAndClickByRole("LINK", "LOGIN PORTAL Login Portal");
        }));
        getBrowserManager().getPage().bringToFront();
    }
}
