package tests;

import framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.EyesException;

public class SampleUiTest {
    private WebDriver driver;
    private EyesRunner runner;
    private Eyes eyes;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY")); // Set your Applitools API key as env variable
        eyes.setBatch(new BatchInfo("UI Visual Tests"));
    }

    @Test
    public void openGoogleTest() {
        driver.get("https://www.google.com");
        // Start visual test
        eyes.open(driver, "Google", "Google Homepage Test", new RectangleSize(1024, 768));
        // Visual checkpoint
        eyes.checkWindow("Google Homepage");
        // Usual assertion
        Assert.assertTrue(driver.getTitle().contains("Google"));
        // End visual test
        eyes.closeAsync();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (eyes != null) {
            try {
                eyes.abortIfNotClosed();
            } catch (EyesException e) {
                // Ignore if already closed
            }
        }
    }
}
