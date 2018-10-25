package com.funda.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QuickSearchPOM {
    public static final String URL = "https://www.funda.nl/";

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/nav/ul/li[1]/a")
    public static WebElement KOOP_TAB;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/nav/ul/li[2]/a")
    public static WebElement HUUR_TAB;

    @FindBy(xpath = "//ul[@class='search-block__expandible-items']//li[1]")
    public static WebElement NIEUWBOUW_TAB;

    @FindBy(xpath = "//ul[@class='search-block__expandible-items']//li[2]")
    public static WebElement RECREATIE_TAB;

    @FindBy(xpath = "//ul[@class='search-block__expandible-items']//li[3]")
    public static WebElement EUROPA_TAB;

    @FindBy(id = "autocomplete-input")
    public static WebElement SEARCH_INPUT;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/div/fieldset[1]/div[1]/button")
    public static WebElement SEARCH_CLEAR_BUTTON;

    @FindBy(id = "Straal")
    public static WebElement DISTANCE_FILTER;

    @FindBy(id = "range-filter-selector-select-filter_huurprijsvan")
    public static WebElement HUUR_MINIMUM_PRICE_FILTER;

    @FindBy(id = "range-filter-selector-select-filter_huurprijstot")
    public static WebElement HUUR_MAXIMUM_PRICE_FILTER;

    @FindBy(id = "range-filter-selector-select-filter_koopprijsvan")
    public static WebElement KOOP_MINIMUM_PRICE_FILTER;

    @FindBy(id = "range-filter-selector-select-filter_koopprijstot")
    public static WebElement KOOP_MAXIMUM_PRICE_FILTER;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/div/div/button")
    public static WebElement SEARCH_BUTTON;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/div/fieldset/div/div/div[1]")
    public static WebElement COUNTRY_FILTER;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/div/fieldset/div/div/div[2]/ul")
    public static List<WebElement> COUNTRY_VALUES;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/p")
    public static WebElement LAST_SEARCH_ITEM;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/p/a")
    public static WebElement LAST_SEARCH_ITEM_TEXT;

    @FindBy(xpath = "//a[@class='logo']")
    public static WebElement FUNDA_LOGO;

    @FindBy(xpath = "//ul[@id='autocomplete-list']")
    public static List<WebElement> SEARCH_SUGGESTION_LIST;

    @FindBy(xpath = "//h4[@class='autocomplete-no-suggestion-message']")
    public static WebElement NO_SUGGESTION_MESSAGE;

    @FindBy(xpath = "//h4[@class='autocomplete-suggestion-message']")
    public static WebElement SUGGESTION_MESSAGE;

    @FindBy(xpath = "//div[@class=\"range-filter-selector is-invalid\"]")
    public static WebElement INVALID_FILTER_RANGE;

    public static boolean isPageDisplayed(WebDriver driver) {
        return (driver.getCurrentUrl().equals(URL));
    }
}
