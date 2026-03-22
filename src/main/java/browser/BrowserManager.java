package browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.*;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
    public Page.WaitForSelectorOptions options;
    public Properties properties;

    private static final Logger logger = Logger.getLogger(BrowserManager.class.getName());

    public BrowserManager() {
        properties = new Properties();
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties").toString()));
        try(InputStream input= Files.newInputStream(configPath)) {
            properties.load(input);
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties file", e);
        }
    }

    public void setUp() {
        logger.info("Setting up Playwright....");
        String browserType = properties.getProperty("browser", "chromium");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Playwright playwright = Playwright.create();
        switch (browserType.toUpperCase()) {
            case "CHROMIUM":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "FIREFOX":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                logger.warning("Unsupported browser type: " + browserType + ". Defaulting to chromium");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
        }
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = browserContext.newPage();
        options = new Page.WaitForSelectorOptions().setTimeout(10000);
        logger.info("Playwright setup complete!");
    }

    public void tearDown() {
        logger.info("Tearing down Playwright...");
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        logger.info("Playwright teardown complete!");
    }

    public byte[] takeScreenshot() {
        if (page != null) {
            return page.screenshot((new Page.ScreenshotOptions().setFullPage(true)));
        }
        return new byte[0];
    }
}
