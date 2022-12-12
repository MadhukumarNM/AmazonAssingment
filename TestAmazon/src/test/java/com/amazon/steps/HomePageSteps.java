package com.amazon.steps;


import com.amazon.pagesObjects.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class HomePageSteps {
    public static String title = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

 private final HomePage homePage;

    public HomePageSteps(HomePage homePage) {
        this.homePage = homePage;
    }


    @When("user select main menu")
    public void selectMainMenu() {
        homePage.selectMainMenu();
    }

    @When("select {string} in Shop By Department")
    public void selectTvAndAppliance(String tv) {
           homePage.selectLeftNavigationMainMenu(tv);
    }

    @Then("{string} should be displayed under submenu TV, Audio & Camera")
    public void should_be_displayed_under(String items) {
        String name = homePage.getElementText();
        Assert.assertEquals(homePage.getElementText(), items);
    }

    @When("user select {string} menu")
    public void user_select_Television_menu(String subMenuItem) {
        homePage.selectLeftNavigationSubMenu(subMenuItem);
    }

    @When("select {string} brand")
    public void select_brand(String brandName) {
        homePage.brandSelection(brandName);
    }



}
