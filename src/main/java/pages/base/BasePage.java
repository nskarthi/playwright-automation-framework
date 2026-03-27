package pages.base;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
    private final BrowserManager browserManager;

    public BasePage(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    protected BrowserManager getBrowserManager () {
        return browserManager;
    }

    public void navigate(String url) {
        browserManager.getPage().navigate(url);
    }

    public void waitAndClickByRole(String role, String name) {
        //getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
        Locator element = browserManager.getPage().getByRole(AriaRole.valueOf(role.toUpperCase()),
                new Page.GetByRoleOptions().setName(name));
        element.click();
    }

    public void waitAndClickBySelector(String selector) {
        browserManager.getPage().waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserManager.getPage().click(selector);
    }

    public void waitAndClickByLocator(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        locator.click();
    }

    public void fillData(String fieldName, String fieldValue){
        getBrowserManager().getPage().getByPlaceholder(fieldName).fill(fieldValue);
    }
}
