package com.funda.quickSearchTests;

import com.funda.pom.QuickSearchPOM;
import com.funda.pom.SearchResultPOM;
import com.funda.testhelper.TestHelper;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class EuropaTests extends BaseTest {

    @Test
    public void verifyEuropaSearchOptions() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.EUROPA_TAB);
        QuickSearchPOM.EUROPA_TAB.click();
        assertTrue("Country drop down is not displayed", TestHelper.isDisplayed(QuickSearchPOM.COUNTRY_FILTER));
        assertTrue("Search button is not displayed", TestHelper.isDisplayed(QuickSearchPOM.SEARCH_BUTTON));
    }

    @Test
    public void verifyEuropaSearchEmpty() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        TestHelper.waitTillElementVisible(QuickSearchPOM.EUROPA_TAB);
        QuickSearchPOM.EUROPA_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.EUROPA_SEARCH_FIELD);
        assertTrue("Expected: Alle landen, Actual:" + SearchResultPOM.EUROPA_SEARCH_FIELD.getText(), SearchResultPOM.EUROPA_SEARCH_FIELD.getText().equals("Alle landen"));
    }

    @Test
    public void verifyEuropaSearchWithCountry() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.EUROPA_TAB);
        QuickSearchPOM.EUROPA_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.COUNTRY_FILTER);
        QuickSearchPOM.COUNTRY_FILTER.click();
        QuickSearchPOM.COUNTRY_VALUES.get(0).click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));
    }
}
