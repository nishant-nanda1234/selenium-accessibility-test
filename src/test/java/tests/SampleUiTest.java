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
import com.applitools.eyes.BatchInfo;


public class SampleUiTest {
    private WebDriver driver;
    private Eyes eyes;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        eyes = new Eyes();
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
        eyes.close();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (eyes != null) {
            eyes.abortIfNotClosed();
        }
    }
}
