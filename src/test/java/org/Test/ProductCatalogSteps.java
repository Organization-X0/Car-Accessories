package org.Test;

import org.Car.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductCatalogSteps {
    App myApp;
    public ProductCatalogSteps(App myApp) {
        this.myApp = myApp;
    }

    @Given("the user is logged in and on product catalog page")
    public void the_user_is_logged_in_and_on_product_catalog_page() {
        myApp.login("user1@gmail.com","u123");
        //myApp.setState();
    }

    @When("the user views the categories")
    public void the_user_views_the_categories() {
    }

    @Then("the user should see categories like {string}, {string}, and {string}")
    public void the_user_should_see_categories_like_and(String string, String string2, String string3) {
    }

    @Given("the user is logged in and on a category page")
    public void the_user_is_logged_in_and_on_a_category_page() {
    }

    @When("the user views the product listings")
    public void the_user_views_the_product_listings() {
    }

    @Then("each listing should show product descriptions, prices, and availability")
    public void each_listing_should_show_product_descriptions_prices_and_availability() {
    }

    @Given("the user is logged in and on the product catalog page")
    public void the_user_is_logged_in_and_on_the_product_catalog_page() {
    }

    @When("the user searches for a product with product name")
    public void the_user_searches_for_a_product_with_product_name() {
    }

    @Then("the user should see filtered product listings")
    public void the_user_should_see_filtered_product_listings() {
    }
}
