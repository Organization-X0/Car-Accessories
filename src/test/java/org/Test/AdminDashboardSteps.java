package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;

public class AdminDashboardSteps {
    App myApp;
    public AdminDashboardSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        myApp.login("admin@gmail.com","a123");
    }

    @When("the admin adds new category")
    public void the_admin_adds_new_category() {
    }

    @Then("a new category should be created")
    public void a_new_category_should_be_created() {
    }

    @When("the admin updates the category")
    public void the_admin_updates_the_category() {
    }

    @Then("the category should be updated")
    public void the_category_should_be_updated() {
    }

    @When("the admin delete category")
    public void the_admin_delete_category() {
    }

    @Then("the category should be deleted")
    public void the_category_should_be_deleted() {
    }

    @When("the admin adds new product")
    public void the_admin_adds_new_product() {

    }

    @Then("a new product listing should be created")
    public void a_new_product_listing_should_be_created() {
    }

    @When("the admin updates the product")
    public void the_admin_updates_the_product() {
    }

    @Then("the product should be updated")
    public void the_product_should_be_updated() {
    }

    @When("the admin delete account")
    public void the_admin_delete_account() {
    }

    @Then("the account should be deleted")
    public void the_account_should_be_deleted() {
    }

    @When("the admin update account")
    public void the_admin_update_account() {
    }

    @Then("the account should be updated")
    public void the_account_should_be_updated() {
    }

    @When("the admin enter manage user accounts")
    public void the_admin_enter_manage_user_accounts() {
    }

    @Then("the user accounts should show")
    public void the_user_accounts_should_show() {
    }

    @When("add new installation appointments")
    public void add_new_installation_appointments() {
    }

    @Then("the appointments should be added")
    public void the_appointments_should_be_added() {
    }

    @When("the admin deletes appiontement")
    public void the_admin_deletes_appiontement() {
    }

    @Then("the appointment should be deleted")
    public void the_appointment_should_be_deleted() {
    }

    @When("admin update appointment")
    public void admin_update_appointment() {
    }

    @Then("the appointment should be updated")
    public void the_appointment_should_be_updated() {

    }


}
