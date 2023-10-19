package org.Test;

import org.Car.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Sates.CustomerDashboardState;
import org.Sates.ProductCatalogState;
import org.Sates.ProductListingState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductCatalogSteps {
    App myApp;

    public ProductCatalogSteps(App myApp) {
        this.myApp = myApp;
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        myApp.login("user1@gmail.com","u123");
    }

    @When("the user enter product catalog")
    public void the_user_enter_product_catalog() {
        myApp.getCurrentState().handleInput("1");

    }

    @Then("the user should see categories")
    public void the_user_should_see_categories() {
        assertTrue(myApp.getCurrentState() instanceof ProductCatalogState);

    }

    @When("the user enter All product or specific category")
    public void the_user_enter_all_product_or_specific_category() {
        myApp.getCurrentState().handleInput("1");
    }

    @Then("each listing should show product descriptions, prices, and availability")
    public void each_listing_should_show_product_descriptions_prices_and_availability() {
        assertTrue(myApp.getCurrentState() instanceof ProductListingState);
    }

    @When("the user enter searche page")
    public void the_user_enter_searche_page() {
        myApp.getCurrentState().handleInput("2");
    }

    @When("searches for a product with product name")
    public void searches_for_a_product_with_product_name() {

    }

    @Then("the user should see filtered product listings")
    public void the_user_should_see_filtered_product_listings() {
        assertTrue(myApp.getCurrentState() instanceof ProductListingState);
    }

}
