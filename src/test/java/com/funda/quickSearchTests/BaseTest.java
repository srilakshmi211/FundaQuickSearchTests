package com.funda.quickSearchTests;

import com.funda.pom.QuickSearchPOM;
import com.funda.pom.SearchResultPOM;
import com.funda.testhelper.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {
    static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperties().get("user.dir") + "/chromedriver");

        if (System.getProperty("browserType").equals("headless")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        } else {
            driver = new ChromeDriver();
        }
        driver.get(QuickSearchPOM.URL);
        driver.manage().window().fullscreen();
        PageFactory.initElements(driver, QuickSearchPOM.class);
        PageFactory.initElements(driver, SearchResultPOM.class);

        String screenshotFolder = System.getProperties().get("user.dir") + "/screenshots";
        TestHelper.deleteFolderIfExists(screenshotFolder);
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TestHelper.snapScreenShot("FAIL", testResult.getName(), driver);
        }
        TestHelper.waitTillElementVisible(QuickSearchPOM.FUNDA_LOGO);
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
