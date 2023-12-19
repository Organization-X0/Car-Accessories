package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.sates.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserProfileSteps {
    App myApp;
    public UserProfileSteps (App myApp) {
        this.myApp=myApp;
    }
    @Given("an customer is logged in and in profile page")
    public void an_customer_is_logged_in_and_in_profile_page() {
        myApp.login("user1@gmail.com","u123");
        myApp.getCurrentState().handleInput("3");
    }
    @When("the customer enter {string}")
    public void the_customer_enter(String option) {
        myApp.getCurrentState().handleInput(option);

    }

    @Then("should be redirected to the edit profile")
    public void should_be_redirected_to_the_edit_profile() {
            assertTrue(myApp.getCurrentState() instanceof UpdateAccountState);
    }

    @Then("should be redirected to the View order history")
    public void should_be_redirected_to_the_view_order_history() {
        assertTrue(myApp.getCurrentState() instanceof ViewOrderHistoryState);
    }

    @Then("should be redirected to the view installation history")
    public void should_be_redirected_to_the_view_installation_history() {
        // Write code here that turns the phrase above into concrete actions

    }


    @Then("should be redirected to the back to customer dashboard")
    public void should_be_redirected_to_the_back_to_customer_dashboard() {
        assertTrue(myApp.getCurrentState() instanceof CustomerDashboardState);
    }


    @When("the customer edit their profile")
    public void the_customer_edit_their_profile() {
      myApp.getCurrentState().handleInput("1");
        Map<String, String> data = new HashMap<>();
        data.put("fullName", "name1");
        data.put("phone", "0568243138");
      myApp.getCurrentState().handleInput(data);
    }

    @Then("the profile should be edited")
    public void the_profile_should_be_edited() {
    assertEquals(myApp.searchAccount(myApp.getEmail()).getFullName(),"name1");
    assertEquals(myApp.searchAccount(myApp.getEmail()).getPhone(),"0568243138");
    }

    @When("the customer navigates to their order history")
    public void the_customer_navigates_to_their_order_history() {
        myApp.getCurrentState().handleInput("2");
    }

    @Then("the customer should be able to view all past orders")
    public void the_customer_should_be_able_to_view_all_past_orders() {
        assertTrue(myApp.getCurrentState() instanceof ViewOrderHistoryState);
    }
    @When("the customer navigates to their installation requests")
    public void the_customer_navigates_to_their_installation_requests() {
        myApp.getCurrentState().handleInput("3");
    }
    @Then("the customer should be able to view all installation requests")
    public void the_customer_should_be_able_to_view_all_installation_requests() {
        assertTrue(myApp.getCurrentState() instanceof ViewInstallationHistoryState);
    }
}