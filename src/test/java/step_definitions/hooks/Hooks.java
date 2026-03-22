package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.*;

import java.util.Collection;

public class Hooks {
    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("\nStarting to execute the test suite...");
    }

    @Before
    public void setup() {
        System.out.println("\nStarting to execute the test case...");
        browserManager.setUp();
    }

    @After
    public void teardown(Scenario scenario) {
        if(scenario.isFailed()) {
            // Get the line number (helpful for Scenario Outlines)
            Integer lineNumber = scenario.getLine();
            scenario.log("Failed at line: " + lineNumber);
            
            // Get the tags as a Collection of Strings
            Collection<String> tags = scenario.getSourceTagNames();
            scenario.log("Scenario Tags: " + tags.toString());

            String fileName = "Failed_" + scenario.getName().replaceAll(" ", "_");
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", fileName);
        }
        System.out.println("\nFinished executing the test case!");
        browserManager.tearDown();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("\nFinished executing the test suite!");
    }
}
