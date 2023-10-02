package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;

public class UserRolesSteps {
    App myApp;
    UserRolesSteps(App myApp){
        this.myApp=myApp;
    }

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the admin accesses the admin dashboard")
    public void the_admin_accesses_the_admin_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the admin should be able to manage products")
    public void the_admin_should_be_able_to_manage_products() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("manage categories")
    public void manage_categories() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("manage user accounts")
    public void manage_user_accounts() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a customer is logged in")
    public void a_customer_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the customer accesses the product catalog")
    public void the_customer_accesses_the_product_catalog() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the customer should be able to browse products")
    public void the_customer_should_be_able_to_browse_products() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("make purchases")
    public void make_purchases() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("view orders")
    public void view_orders() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("an installer is logged in")
    public void an_installer_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
//        assertEquals(myApp.getEmail(),"admin@gmail.com");
    }
    @When("the installer accesses their dashboard")
    public void the_installer_accesses_their_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the installer should be able to view installation requests")
    public void the_installer_should_be_able_to_view_installation_requests() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("schedule appointments")
    public void schedule_appointments() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
