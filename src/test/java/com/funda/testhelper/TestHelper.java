package com.funda.testhelper;

import com.funda.pom.QuickSearchPOM;
import com.funda.quickSearchTests.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TestHelper {

    private static final int TIME_SECONDS = 10;
    private static WebDriver driver;


    public static String generateString(int length) {
        String generatedString = RandomStringUtils.randomAlphanumeric(length);
        return generatedString;
    }

    public static String setRandomCity() {
        String[] cities = {"Amsterdam", "Amstelveen", "Den Haag", "Rotterdam", "Haarlem", "Utrecht", "Eindhoven"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static void snapScreenShot(String result, String name, WebDriver driver) throws IOException {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = System.getProperties().get("user.dir") + "/screenshots";
        File directory = new File(fileName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        fileName = fileName + "/" + name + "_" + result + ".jpg";
        File destinationFile = new File(fileName);
        FileHandler.copy(sourceFile, destinationFile);
    }

    public static void waitTillElementVisible(WebElement element) {
        driver = BaseTest.getDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIME_SECONDS);
        webDriverWait.ignoring(WebDriverException.class);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            waitTillElementVisible(element);
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }


    public static void enterSearchAddress() {
        String city = TestHelper.setRandomCity();
        waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys(city);
        waitTillElementVisible(QuickSearchPOM.SEARCH_SUGGESTION_LIST.get(0));
        QuickSearchPOM.SEARCH_SUGGESTION_LIST.get(0).click();
    }

    public static void deleteFolderIfExists(String folderName) {
        Path folder = Paths.get(folderName);
        if (Files.exists(folder)) {
            FileHandler.delete(new File(folderName));
        }
    }

    public static boolean isLastSearchItemSame(String str, WebElement element) {
        try {
            TestHelper.waitTillElementVisible(element);
            if (str.equals(element.getText())) {
                return true;
            }
        } catch (NoSuchElementException nse) {
            return false;
        } catch (TimeoutException te) {
            return false;
        }
        return false;
    }
}
