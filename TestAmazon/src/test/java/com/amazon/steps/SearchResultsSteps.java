package com.amazon.steps;

import com.amazon.pagesObjects.SearchResultsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class SearchResultsSteps {

    private SearchResultsPage searchResultsPage;

    public SearchResultsSteps(SearchResultsPage searchResultsPage) {
        this.searchResultsPage = searchResultsPage;
    }

    @Then("{string} should be displayed in results panel")
    public void should_be_displayed_in_results_panel(String value) {
        Assert.assertTrue(searchResultsPage.searchResultsText(value));
    }

    @When("I select dropdown value {string}")
    public void i_select_dropdown_value(String name) throws InterruptedException {
        searchResultsPage.sortByHighToLow(name);
    }

    @Then("Records should be sorted on price high to low")
    public void records_should_be_sorted_on_price_high_to_low() {

    }

    @When("select {string}nd priced item")
    public void select_nd_priced_item(String item) throws InterruptedException {
        searchResultsPage.selectFilteredResult(item);
    }

    @Then("{string} should be present in next window {int}")
    public void should_be_present(String name, int index) throws InterruptedException, IOException {
        Boolean actualText = searchResultsPage.aboutThisItemInNextWindow(index);
        Assert.assertTrue(actualText);
    }
}
