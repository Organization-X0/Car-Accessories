package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Data.Product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        myApp.addCategory("cat1");
    }

    @Then("a new category should be created")
    public void a_new_category_should_be_created() {
        assertTrue(myApp.searchCategory("cat1"));
    }

    @When("the admin updates the category")
    public void the_admin_updates_the_category() {
        myApp.updateCategory("Interior","newcat1");
    }

    @Then("the category should be updated")
    public void the_category_should_be_updated() {
        assertTrue(myApp.searchCategory("newcat1"));
    }

    @When("the admin delete category")
    public void the_admin_delete_category() {
        myApp.deleteCategory("Interior");
    }

    @Then("the category should be deleted")
    public void the_category_should_be_deleted() {
        myApp.searchCategory("Interior");
    }

    @When("the admin adds new product")
    public void the_admin_adds_new_product() {
        myApp.addProduct("item4","Interior","dont buy this",55.5);

    }

    @Then("a new product listing should be created")
    public void a_new_product_listing_should_be_created() {
        assertTrue(myApp.searchProduct(1));
    }

    @When("the admin updates the product")
    public void the_admin_updates_the_product() {
        Product product=new Product();
        product.setName("meooo");
        myApp.updateProduct(1,product);
    }

    @Then("the product should be updated")
    public void the_product_should_be_updated() {
        // come back
    }
    @When("the admin delete product")
    public void the_admin_delete_product() {
        myApp.deleteProduct(1);
    }

    @Then("the product should be deleted")
    public void the_product_should_be_deleted() {
        assertFalse(myApp.searchProduct(1));
    }

    @When("the admin delete account")
    public void the_admin_delete_account() {
        myApp.deleteAccount("user1@gmail.com");
    }

    @Then("the account should be deleted")
    public void the_account_should_be_deleted() {
       assertFalse(myApp.searchAccount("user1@gmail.com"));
    }

    @When("the admin update account")
    public void the_admin_update_account() {
        myApp.updateAccount("user1@gmail.com","","mode");
    }

    @Then("the account should be updated")
    public void the_account_should_be_updated() {
// come back
    }

    @When("the admin enter manage user accounts")
    public void the_admin_enter_manage_user_accounts() {
       // assertTrue(myApp.state,State.);
    }

    @Then("the user accounts should show")
    public void the_user_accounts_should_show() {
        assertTrue(myApp.isAccountShown);
    }

    @When("add new installation appointments")
    public void add_new_installation_appointments() {
        myApp.newAppointment("user1@gmail.com","item1","bmw","10-4-2023");
    }

    @Then("the appointments should be added")
    public void the_appointments_should_be_added() {
        assertTrue(myApp.searchAppointment("1"));
    }

    @When("the admin deletes appiontement")
    public void the_admin_deletes_appiontement() {
        myApp.deleteAppointment("1");
    }

    @Then("the appointment should be deleted")
    public void the_appointment_should_be_deleted() {
        assertFalse(myApp.searchAppointment("1"));
    }

    @When("admin update appointment")
    public void admin_update_appointment() {
        //come back

    }

    @Then("the appointment should be updated")
    public void the_appointment_should_be_updated() {
      //come back
    }


}
