package com.funda.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPOM {

    @FindBy(id = "autocomplete-input")
    public static WebElement SEARCH_INPUT;

    @FindBy(id = "Straal")
    public static WebElement DISTANCE_FILTER;

    @FindBy(xpath = "/html[1]/body[1]/main[1]/form[1]/div[2]/div[1]")
    public static WebElement SEARCH_RESULT_MESSAGE;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div[1]/div/div/div/div/div[1]")
    public static WebElement EUROPA_SEARCH_FIELD;

}


