package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

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
    public void teardown() {
        System.out.println("\nFinished executing the test case!");
        browserManager.tearDown();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("\nFinished executing the test suite!");
    }
}
