package com.amazon.webdriverfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DriverHelperImpl implements DriverHelper{

    @Autowired
    ApplicationSetup applicationSetup;

    @Value("${default.timeout}")
    private int timeout;

    @Override
    public void click(By by) {
        this.waitForElement(by);
        applicationSetup.getDriver().findElement(by).click();
    }

    @Override
    public void fillTextBox(By by, String value) {
        WebElement ele = applicationSetup.getDriver().findElement(by);
        ele.clear();
        ele.sendKeys(value);
    }

    @Override
    public void waitForElement(By by) {
        applicationSetup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    @Override
    public void clickByJS(By by) {
        this.waitForElement(by);
        JavascriptExecutor je = (JavascriptExecutor) applicationSetup.getDriver();
        WebElement webElement = applicationSetup.getDriver().findElement(by);
        je.executeScript("arguments[0].click()", webElement);
    }

    @Override
    public String getElementText(By by) {
        String text = applicationSetup.getDriver().findElement(by).getText();
        return text;
    }

    @Override
    public void switchToWindow(int index) {
        Set<String> windows = applicationSetup.getDriver().getWindowHandles();
        int totalWin= windows.size();
        String winTitle = null;
        for(int i=0;i<totalWin;i++) {
            if(i==index) {
                winTitle = windows.toArray()[i].toString();
            }
        }
        applicationSetup.getDriver().switchTo().window(winTitle);
        System.out.println(winTitle);
    }

    @Override
    public void waitForInvisibilityOfElement(By by) {
        WebElement ele = applicationSetup.getDriver().findElement(by);
            WebDriverWait wait = new WebDriverWait(applicationSetup.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    @Override
    public void waitForVisibilityOfElement(By by) {
        WebElement ele = applicationSetup.getDriver().findElement(by);
        WebDriverWait wait = new WebDriverWait(applicationSetup.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    @Override
    public void switchToFrameByIndex(int index) {
        applicationSetup.getDriver().switchTo().frame(index);
    }

    public String getPageSource(){
        return applicationSetup.getDriver().getPageSource();
    }

    @Override
    public List<String> getElementsText(By by) {
        List<String> newList = new ArrayList<String>();
        List<WebElement> ls = applicationSetup.getDriver().findElements(by);
        ls.stream().forEach(s->newList.add(s.getText()));
        return newList;
    }

}
