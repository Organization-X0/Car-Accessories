package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;

import static org.junit.Assert.assertTrue;

public class ProductCatalogSteps {
    App myApp;
    public ProductCatalogSteps(App myApp){
        this.myApp=myApp;
    }

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        myApp.login("admin@gmail.com","a123");
    }
    @When("the admin adds a new product with valid details")
    public void the_admin_adds_a_new_product_with_valid_details() {
        myApp.addProduct();
    }
    @Then("the product should be added to the catalog")
    public void the_product_should_be_added_to_the_catalog() {
        assertTrue(myApp.isProductAdded());
    }

    @When("the admin updates the product with new details")
    public void the_admin_updates_the_product_with_new_details() {
        myApp.updateProduct();
    }
    @Then("the product details should be updated in the catalog")
    public void the_product_details_should_be_updated_in_the_catalog() {
        assertTrue(myApp.isProductUpdated());
    }

    @When("the admin deletes the product")
    public void the_admin_deletes_the_product() {
        myApp.deleteProduct();
    }
    @Then("the product should be removed from the catalog")
    public void the_product_should_be_removed_from_the_catalog() {
        assertTrue(myApp.isProductDeleted());
    }
    @Given("a customer is logged in")
    public void a_customer_is_logged_in() {
        myApp.login("user1@gmail.com","u123");
    }
    @When("the customer browses the product catalog")
    public void the_customer_browses_the_product_catalog() {
        myApp.browseProducts();
    }
    @Then("they should see a list of available products")
    public void they_should_see_a_list_of_available_products() {
        assertTrue(myApp.isBrowsed());
    }

    @When("the customer searches for a product by name")
    public void the_customer_searches_for_a_product_by_name() {
        myApp.searchProduct();
    }
    @Then("they should see a list of matching products in the catalog")
    public void they_should_see_a_list_of_matching_products_in_the_catalog() {
        assertTrue(myApp.isSearched());
    }
}
