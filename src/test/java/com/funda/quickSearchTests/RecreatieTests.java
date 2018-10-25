package com.funda.quickSearchTests;

import com.funda.pom.QuickSearchPOM;
import com.funda.pom.SearchResultPOM;
import com.funda.testhelper.TestHelper;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class RecreatieTests extends BaseTest {

    @Test
    public void verifyRecreatieSearchOptions() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        assertTrue("Search text field is not displayed", TestHelper.isDisplayed(QuickSearchPOM.SEARCH_INPUT));
        assertTrue("Distance filter is not displayed", TestHelper.isDisplayed(QuickSearchPOM.DISTANCE_FILTER));
        assertTrue("Search button is not displayed", TestHelper.isDisplayed(QuickSearchPOM.SEARCH_BUTTON));
    }

    @Test
    public void verifyRecreatieSearchEmpty() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected: Nederland, Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").equals("Nederland"));
    }

    @Test
    public void verifyRecreatieSearchWithAddress() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
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
    public void verifyRecreatieSearchWithAddressAsPostcode() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
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
    public void verifyRecreatieSearchMayBeYouMeanList() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys("Haaz");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        assertTrue("May be you mean list is not displayed, refer screenshot", TestHelper.isDisplayed(QuickSearchPOM.SUGGESTION_MESSAGE));
    }

    @Test
    public void verifyRecreatieLastSearchAddress() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        String expectedText = SearchResultPOM.SEARCH_INPUT.getAttribute("value");
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        assertTrue("Expected:" + expectedText + " Actual:" + QuickSearchPOM.LAST_SEARCH_ITEM.getText(), QuickSearchPOM.LAST_SEARCH_ITEM.getText().equals("Laatste zoekopdracht: " + expectedText));
    }

    @Test
    public void verifyRecreatieLastSearchClick() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.enterSearchAddress();
        String expectedText = QuickSearchPOM.SEARCH_INPUT.getAttribute("value");
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        QuickSearchPOM.LAST_SEARCH_ITEM_TEXT.click();
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_INPUT);
        assertTrue("Expected:" + expectedText + " Actual:" + SearchResultPOM.SEARCH_INPUT.getAttribute("value"), SearchResultPOM.SEARCH_INPUT.getAttribute("value").contains(expectedText));
        TestHelper.waitTillElementVisible(SearchResultPOM.SEARCH_RESULT_MESSAGE);
        assertTrue(SearchResultPOM.SEARCH_RESULT_MESSAGE.getText().contains("resultaten"));

    }

    @Test
    public void verifyRecreatieInvalidAddressSearch() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        String city = TestHelper.generateString(10);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys(city);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        TestHelper.waitTillElementVisible(QuickSearchPOM.NO_SUGGESTION_MESSAGE);
        assertTrue("No suggestion text is not displayed", QuickSearchPOM.NO_SUGGESTION_MESSAGE.getText().equals("Ai! Deze locatie kunnen we helaas niet vinden."));
    }

    @Test
    public void verifyRecreatieClearEnteredCityName() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        String city = TestHelper.generateString(10);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        QuickSearchPOM.SEARCH_INPUT.sendKeys(city);
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_CLEAR_BUTTON);
        QuickSearchPOM.SEARCH_CLEAR_BUTTON.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_INPUT);
        assertTrue("Text is not cleared", QuickSearchPOM.SEARCH_INPUT.getAttribute("value").equals(""));
    }

    @Test
    public void verifyRecreatieLastSearchDoesntAffectOtherTabs() {
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.enterSearchAddress();
        TestHelper.waitTillElementVisible(QuickSearchPOM.SEARCH_BUTTON);
        QuickSearchPOM.SEARCH_BUTTON.click();
        QuickSearchPOM.FUNDA_LOGO.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.RECREATIE_TAB);
        QuickSearchPOM.RECREATIE_TAB.click();
        TestHelper.waitTillElementVisible(QuickSearchPOM.LAST_SEARCH_ITEM);
        String lastSearchText = QuickSearchPOM.LAST_SEARCH_ITEM.getText();

        TestHelper.waitTillElementVisible(QuickSearchPOM.KOOP_TAB);
        QuickSearchPOM.KOOP_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.HUUR_TAB);
        QuickSearchPOM.HUUR_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.NIEUWBOUW_TAB);
        QuickSearchPOM.NIEUWBOUW_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

        TestHelper.waitTillElementVisible(QuickSearchPOM.EUROPA_TAB);
        QuickSearchPOM.EUROPA_TAB.click();
        assertTrue(!(TestHelper.isLastSearchItemSame(lastSearchText, QuickSearchPOM.LAST_SEARCH_ITEM)));

    }
}
