package com.amazon.webdriverfactory;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSetup {
    @Autowired
    WebDriverManager webDriverManager;
    WebDriver driver;

    public void init(){
        driver = webDriverManager.startBrowser();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
