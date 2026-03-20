package browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.*;


import java.awt.*;

public class BrowserManager {
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;

    public void setUp() {
        System.out.println("Setting up Playwright....");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = browserContext.newPage();
        System.out.println("Playwright setup complete!");
    }

    public void tearDown() {
        System.out.println("Tearing down Playwright...");
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        System.out.println("Playwright teardown complete!");
    }
}
