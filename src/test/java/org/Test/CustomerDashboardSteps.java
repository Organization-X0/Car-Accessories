package org.Test;

import org.Car.App;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.Error;
import org.Sates.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerDashboardSteps {
    App myApp;
    int size;
    public CustomerDashboardSteps(App myApp){
        this.myApp=myApp;
        size=0;
    }

    @Given("an customer is logged in")
    public void an_customer_is_logged_in() {
        myApp.login("user1@gmail.com","u123");
    }
    @When("the customer enters {string}")
    public void the_customer_enters(String option) {
        myApp.getCurrentState().handleInput(option);

    }
    @Then("should be redirected to the Product Catalog")
    public void should_be_redirected_to_the_product_catalog() {
        assertTrue(myApp.getCurrentState() instanceof ProductCatalogState);
    }

    @Then("should be redirected to the Request Services")
    public void should_be_redirected_to_the_request_services() {
        assertTrue(myApp.getCurrentState() instanceof AddAppointmentState);
    }

    @Then("should be redirected to Profile Page")
    public void should_be_redirected_to_profile_page() {
        assertTrue(myApp.getCurrentState() instanceof ProfileState);
    }

    @Then("should see an error message on customer Dashboard")
    public void should_see_an_error_message_on_customer_dashboard() {
        assertEquals(myApp.getCurrentState().getStateString(), Error.getLocation());
    }
    @Then("should be redirected to the Notification Center")
    public void should_be_redirected_to_the_notification_center() {
        assertTrue(myApp.getCurrentState() instanceof NotificationCenterState);
    }

    @Given("customer enter product catalog page and choose category")
    public void customer_enter_product_catalog_page_and_choose_category() {


    }
    @Given("the customer enters the product catalog page and chooses the {string}")
    public void the_customer_enters_the_product_catalog_page_and_chooses_the(String categoryNum) {
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput(categoryNum);
    }
    @When("customer buys product from product catalog")
    public void customer_buys_product_from_product_catalog() {
        size=myApp.searchAccount(myApp.email).getOrders().size();

    }
    @When("the customer buys {string} from the product catalog")
    public void the_customer_buys_from_the_product_catalog(String product) {
        myApp.getCurrentState().handleInput("f"+product);
    }
    @Then("the purchase should be completed")
    public void the_purchase_should_be_completed() {
        assertEquals(myApp.searchAccount(myApp.email).getOrders().size(),size+1);
    }
    @Given("customer enter profile page {string}")
    public void customer_enter_profile_page(String option) {

        myApp.getCurrentState().handleInput(option);
    }

    @When("enter show orders history {string}")
    public void enter_show_orders_history(String option) {
        myApp.getCurrentState().handleInput(option);
    }



    @Then("the orders history should be shown")
    public void the_orders_history_should_be_shown() {
        assertTrue(myApp.getCurrentState() instanceof ViewOrderHistoryState);
    }
}
