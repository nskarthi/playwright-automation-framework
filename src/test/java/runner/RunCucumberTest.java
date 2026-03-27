package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "not @skip",  // not @skip excludes any scenario with the @skip tag
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-report.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Logger logger = Logger.getLogger(RunCucumberTest.class.getName());
    private static final Properties properties = new Properties();

    //static block to load properties file
    static {
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties").toString()));
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties file", e);
        }
    }

    public static void main(String[] args) {
        //create an instance of TestNG
        TestNG testNG = new TestNG();

        //Create a new TestNG suite
        XmlSuite xmlSuite = new XmlSuite();

        int threadCount = getThreadCount();
        System.out.println("Configured thread count value: " + threadCount);
        xmlSuite.setDataProviderThreadCount(threadCount);

        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Cucumber Tests");
        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(RunCucumberTest.class)));

        testNG.setXmlSuites(Collections.singletonList(xmlSuite));
        testNG.run();
    }

    private static int getThreadCount() {
        return Integer.parseInt(properties.getProperty("thread.count", "1"));
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
