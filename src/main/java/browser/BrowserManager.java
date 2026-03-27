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
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    public Page.WaitForSelectorOptions options;
    public Properties properties;

    private static final Logger logger = Logger.getLogger(BrowserManager.class.getName());

    public BrowserManager() {
        properties = new Properties();
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties").toString()));
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties file", e);
        }
    }

    public void setUp() {
        logger.info("Setting up Playwright....");
        String browserType = properties.getProperty("browser", "chromium");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        try {
            playwright.set(Playwright.create());
            switch (browserType.toUpperCase()) {
                case "CHROMIUM":
                    browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                case "FIREFOX":
                    browser.set(playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                default:
                    logger.warning("Unsupported browser type: " + browserType + ". Defaulting to chromium");
                    browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
            }
            browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(width, height)));
            page.set(browserContext.get().newPage());
            options = new Page.WaitForSelectorOptions().setTimeout(10000);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to setup Playwright!", e);
            throw new RuntimeException(e);
        }
        logger.info("Playwright setup complete!");
    }

    public Page getPage() {
        return page.get();
    }

    public void setPage(Page newPage) {
        page.set(newPage);
    }

    public BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public void setBrowserContext(BrowserContext context) {
        browserContext.set(context);
    }

    public void tearDown() {
        logger.info("Tearing down Playwright...");
        try {
            if (page.get() != null) page.get().close();
            if (browserContext.get() != null) browserContext.get().close();
            if (browser.get() != null) browser.get().close();
            if (playwright.get() != null) playwright.get().close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to close Playwright resources!!", e);
        }
        logger.info("Playwright teardown complete!");
    }

    public byte[] takeScreenshot() {
        if (page.get() != null) {
            return page.get().screenshot((new Page.ScreenshotOptions().setFullPage(true)));
        }
        return new byte[0];
    }
}
