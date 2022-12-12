package com.amazon.pagesObjects;

import com.amazon.webdriverfactory.DriverHelper;
import com.amazon.utils.WriteToTextFile;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SearchResultsPage {

    @Value("${expected}")
    private String expected;
    private final DriverHelper driverHelper;

    public SearchResultsPage(DriverHelper driverHelper) {
        this.driverHelper = driverHelper;
    }


    private final By featureDropdown = By.cssSelector("span[data-action='a-dropdown-button']");
    //search results
    private String priceHighToLow = "//li[@class='a-dropdown-item']/a[text()='%s']";

    private String selectParticularTV = "//img[@data-image-index='%s']";

    private String searchResultsString = "//a/span[contains(text(),'%s')]/ancestor::span[@data-component-type='s-search-results']";

    private final By aboutThisItem = By.xpath("//*[@id='feature-bullets']/h1");

    private final By spinElement = By.cssSelector("a-spinner a-spinner-medium");

    private final By backGroundHeader = By.cssSelector("[data-testid='ilmBackground']");

    private final By aboutSectionText = By.xpath("//div[@id='feature-bullets']//ul/li");


    public void sortByHighToLow(String value) throws InterruptedException {
        Thread.sleep(3000);
        priceHighToLow = String.format(priceHighToLow, value);
        driverHelper.clickByJS(featureDropdown);
        driverHelper.click(By.xpath(priceHighToLow));
//        driverHelper.waitForInvisibilityOfElement(spinElement);
    }

    public void selectFilteredResult(String tileName) throws InterruptedException {
        selectParticularTV = String.format(selectParticularTV, tileName);
        Thread.sleep(3000);
        driverHelper.waitForElement(By.xpath(selectParticularTV));
        driverHelper.click(By.xpath(selectParticularTV));
    }

    public boolean searchResultsText(String value) {
        searchResultsString = String.format(searchResultsString, value);
        return true;
    }

    public Boolean aboutThisItemInNextWindow(int index) throws InterruptedException, IOException {
        boolean status = false;
        driverHelper.switchToWindow(index);
        WriteToTextFile file = new WriteToTextFile();
        Thread.sleep(3000);
        List<String> ls = driverHelper.getElementsText(aboutSectionText);
        file.writeToTextFile(ls);
        for (String s : ls) {
            System.out.println(s);

            if(expected.contains(s)){
                status = true;
            }
            else {
                return false;
            }
        }
        return status;
    }




}
