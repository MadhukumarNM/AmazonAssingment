package com.amazon.steps;


import com.amazon.webdriverfactory.ApplicationSetup;
import com.amazon.webdriverfactory.DriverHelper;
import com.amazon.webdriverfactory.SpringConfiguration;
import com.aventstack.extentreports.reporter.FileUtil;
import com.mongodb.MapReduceCommand;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SpringConfiguration.class})
public class CucumberHooks {

    @Autowired
    ApplicationSetup applicationSetup;
    @Value("${url}")
    private String url;


    @Before
    public void beforeScenario() {
        applicationSetup.init();
        applicationSetup.getDriver().get(url);
        applicationSetup.getDriver().manage().window().maximize();
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.out", "test-output/ExtentSpark.html");
    }

    @After
    public void afterScenario() {
        applicationSetup.getDriver().close();
        applicationSetup.getDriver().quit();
    }

    @AfterStep()
    public void takeScreenShotOnFailure(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {

            File ts = ((TakesScreenshot) applicationSetup.getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(ts);
            scenario.attach(fileContent, "image/png", "screenshot");
        }
    }
}