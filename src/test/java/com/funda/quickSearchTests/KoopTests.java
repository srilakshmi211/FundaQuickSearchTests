package com.funda.quickSearchTests;

import com.funda.pom.QuickSearchPOM;
import com.funda.pom.SearchResultPOM;
import com.funda.testhelper.TestHelper;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class KoopTests extends BaseTest {

    @Test
    public void verifyAppLaunch() {

        assertTrue("Funda webpage is not opening", QuickSearchPOM.isPageDisplayed(driver));
    }

    @Test
    public void verifyKoopSearchOptions() {
        QuickSearchPOM.KOOP_TAB.click();
        assertTrue("Search text field is not displayed", TestHelper.isDisplayed(QuickSearchPOM.SEARCH_INPUT));
        assertTrue("Distance filter is not displayed", TestHelper.isDisplayed(QuickSearchPOM.DISTANCE_FILTER));
        assertTrue("Min price filter is not displayed", TestHelper.isDisplayed(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER));
        assertTrue("Max price filter is not displayed", TestHelper.isDisplayed(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER));
        assertTrue("Search button is not displayed", TestHelper.isDisplayed(QuickSearchPOM.SEARCH_BUTTON));
    }

    @Test
    public void verifyKoopSearchEmpty() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected: Nederland, Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").equals("Nederland"));
    }

    @Test
    public void verifyKoopSearchWithOnlyAddress() {
        TestHelper.enterSearchAddress();
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected:" + expectedText + " Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").equals(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));
    }

    @Test
    public void verifyKoopSearchWithAddressAsPostcode() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys("1186");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_SUGGESTION_LIST.get(0));
        QuickSearchPOM.SEARCH_SUGGESTION_LIST.get(0).click();
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected:" + expectedText + " Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").equals(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));
    }

    @Test
    public void verifyKoopSearchWithAllDetails() {
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.DISTANCE_FILTER);
        Select DISTANCE = new Select(QuickSearchPOM.DISTANCE_FILTER);
        DISTANCE.selectByIndex(1);
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        Select MIN_PRICE = new Select(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        MIN_PRICE.selectByValue("300000");
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        Select MAX_PRICE = new Select(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        MAX_PRICE.selectByValue("400000");
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected:" + expectedText + " Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").equals(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));
    }


    @Test
    public void verifyKoopSearchMayBeYouMeanList() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys("Haaz");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        assertTrue("May be you mean list is not displayed, refer screenshot", TestHelper.isDisplayed(QuickSearchPOM.SUGGESTION_MESSAGE));
    }

    @Test
    public void verifyKoopLastSearchAddress() {
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        String expectedText = SearchResultPOM.SEARCH_INPUT.getAttribute("value");
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        assertTrue("Expected:" + expectedText + " Actual:" + QuickSearchPOM.LAST_SEARCH_ITEM.getText(), QuickSearchPOM.LAST_SEARCH_ITEM.getText().equals("Laatste zoekopdracht: " + expectedText));
    }

    @Test
    public void verifyKoopLastSearchWithAllDetails() {
        TestHelper.enterSearchAddress();
        String distance = "+1km";
        String min_value = "€ 50.000";
        String max_value = "€ 100.000";
        TestHelper.waitTillElementVisible(QuickSearchPOM.DISTANCE_FILTER);
        Select DISTANCE = new Select(QuickSearchPOM.DISTANCE_FILTER);
        DISTANCE.selectByIndex(1);
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        Select MIN_PRICE = new Select(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        MIN_PRICE.selectByValue("50000");
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        Select MAX_PRICE = new Select(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        MAX_PRICE.selectByValue("100000");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        String expectedText = SearchResultPOM.SEARCH_INPUT.getAttribute("value");
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        assertTrue("Expected:" + expectedText + ", " + distance + ", " + min_value + " - " + max_value + " Actual:" + QuickSearchPOM.LAST_SEARCH_ITEM.getText(), QuickSearchPOM.LAST_SEARCH_ITEM.getText().equals("Laatste zoekopdracht: " + expectedText + ", " + distance + ", " + min_value + " - " + max_value));
    }

    @Test
    public void verifyKoopLastSearchClick() {
        TestHelper.enterSearchAddress();
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        QuickSearchPOM.LAST_SEARCH_ITEM_TEXT.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected:" + expectedText + " Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").contains(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));

    }

    @Test
    public void verifyKoopLastSearchInfoByClickingBrowserBack() {
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        String expectedText = SearchResultPOM.SEARCH_INPUT.getAttribute("value");
        driver.navigate().back();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        assertTrue("Last search details not corrected, expected: Laatste zoekopdracht: " + expectedText + "but actual:" + QuickSearchPOM.LAST_SEARCH_ITEM.getText(), QuickSearchPOM.LAST_SEARCH_ITEM.getText().equals("Laatste zoekopdracht: " + expectedText));
    }


    @Test
    public void verifyKoopSearchWithDistanceLimit() {
        TestHelper.enterSearchAddress();
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        String distance = "+ 2 km";
        TestHelper.waitTillElementVisible(QuickSearchPOM.DISTANCE_FILTER);
        Select DISTANCE1 = new Select(QuickSearchPOM.DISTANCE_FILTER);
        DISTANCE1.selectByIndex(2);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue(SearchResultPOM.SEARCH_INPUT.getAttribute("value").contains(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.DISTANCE_FILTER);
        Select DISTANCE2 = new Select(SearchResultPOM.DISTANCE_FILTER);
        assertTrue("Distance on quick search page amd search result page are not same", distance.equals(DISTANCE2.getFirstSelectedOption().getText()));
    }

    @Test
    public void verifyKoopInvalidAmountFilterRange() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        Select MIN_PRICE = new Select(QuickSearchPOM.KOOP_MINIMUM_PRICE_FILTER);
        MIN_PRICE.selectByValue("100000");
        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        Select MAX_PRICE = new Select(QuickSearchPOM.KOOP_MAXIMUM_PRICE_FILTER);
        MAX_PRICE.selectByValue("50000");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.NO_SUGGESTION_MESSAGE);
        assertTrue("Max price box does not show error", TestHelper.isDisplayed(QuickSearchPOM.INVALID_FILTER_RANGE));
    }

    @Test
    public void verifyKoopInvalidAddressSearch() {
        String city = TestHelper.generateString(10);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys(city);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue("No suggestion text is not displayed", QuickSearchPOM.NO_SUGGESTION_MESSAGE.getText().equals("Ai! Deze locatie kunnen we helaas niet vinden."));
    }

    @Test
    public void verifyKoopClearEnteredCityName() {
        String city = TestHelper.generateString(10);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys(city);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_CLEAR_BUTTON);
        QuickSearchPOM.SEARCH_CLEAR_BUTTON.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        assertTrue("Text is not cleared", QuickSearchPOM.SEARCH_INPUT.getAttribute("value").equals(""));
    }

    @Test
    public void verifyKoopLastSearchDoesntAffectOtherTabs() {
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        String lastSearchText = QuickSearchPOM.LAST_SEARCH_ITEM.getText();

        TestHelper.waitTillElementVisible(QuickSearchPOM.HUUR_TAB);
        QuickSearchPOM.HUUR_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.NIEUWBOUW_TAB);
        QuickSearchPOM.NIEUWBOUW_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.EUROPA_TAB);
        QuickSearchPOM.EUROPA_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

    }
}
