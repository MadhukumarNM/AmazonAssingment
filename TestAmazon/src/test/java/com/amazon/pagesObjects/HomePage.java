package com.amazon.pagesObjects;

import com.amazon.webdriverfactory.DriverHelper;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

    private final DriverHelper driverHelper;

    public HomePage(DriverHelper driverHelper) {
        this.driverHelper = driverHelper;
    }

    private By mainHomeMenu = By.id("nav-hamburger-menu");

    private String leftNavigationMainMenu = "//div[text()='%s']";

    private String lefNavigationSubMenu = "//a[text()='%s']";

    private String brandLocator = "//*[text()='Brands']//following::ul//span[text()='%s']";

    private By televisionMenu = By.cssSelector("a[href*='television']");

    public void selectMainMenu() {
        driverHelper.click(mainHomeMenu);
    }

    public void selectLeftNavigationMainMenu(String menuName) {
        String expectedString = String.format(leftNavigationMainMenu, menuName);
        driverHelper.waitForElement(By.xpath(expectedString));
        driverHelper.click(By.xpath(expectedString));
    }

    public void selectLeftNavigationSubMenu(String subMenu) {
        lefNavigationSubMenu = String.format(lefNavigationSubMenu, subMenu);
        driverHelper.click(By.xpath(lefNavigationSubMenu));
    }

    public void brandSelection(String brandName) {
        brandLocator = String.format(brandLocator, brandName);
        driverHelper.click(By.xpath(brandLocator));
    }

    public String getElementText() {
        return driverHelper.getElementText(televisionMenu);
    }

}
