package com.amazon.webdriverfactory;

import org.openqa.selenium.By;

import java.util.List;

public interface DriverHelper {

    void click(By by);

    void fillTextBox(By by, String value);

    void waitForElement(By by);

    void clickByJS(By by);

    String getElementText(By by);

    void switchToWindow(int index);

    void waitForInvisibilityOfElement(By by);

    void waitForVisibilityOfElement(By by);

    void switchToFrameByIndex(int index);

    String getPageSource();

    List<String> getElementsText(By by);
}
