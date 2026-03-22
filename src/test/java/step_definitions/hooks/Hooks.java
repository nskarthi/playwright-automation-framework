package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.*;

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
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        System.out.println("\nFinished executing the test case!");
        browserManager.tearDown();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("\nFinished executing the test suite!");
    }
}
