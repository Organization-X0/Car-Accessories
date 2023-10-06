package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.State;
import org.Data.Appointment;
import org.Data.Product;
import org.Data.User;

import static org.junit.Assert.*;

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
        assertNotNull(myApp.searchCategory("cat1"));
    }

    @When("the admin updates the category")
    public void the_admin_updates_the_category() {
        myApp.updateCategory("Interior","new cat1");
    }

    @Then("the category should be updated")
    public void the_category_should_be_updated() {
        assertNotNull(myApp.searchCategory("new cat1"));
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
        myApp.addProduct("item4","Interior","don't buy this",55.5);

    }

    @Then("a new product listing should be created")
    public void a_new_product_listing_should_be_created() {
        assertNotNull(myApp.searchProduct(1));
    }

    @When("the admin updates the product")
    public void the_admin_updates_the_product() {
        Product product=new Product();
        product.setName("new name");
        myApp.updateProduct(1,product);
    }

    @Then("the product should be updated")
    public void the_product_should_be_updated() {
        assertEquals(myApp.searchProduct(1).getName(),"new name");
    }
    @When("the admin delete product")
    public void the_admin_delete_product() {
        myApp.deleteProduct(1);
    }

    @Then("the product should be deleted")
    public void the_product_should_be_deleted() {
        assertNull(myApp.searchProduct(1));
    }

    @When("the admin delete account")
    public void the_admin_delete_account() {
        myApp.deleteAccount("user1@gmail.com");
    }

    @Then("the account should be deleted")
    public void the_account_should_be_deleted() {
       assertNull(myApp.searchAccount("user1@gmail.com"));
    }

    @When("the admin update account")
    public void the_admin_update_account() {
        User user=new User();
        user.setFullName("new name");
        user.setPhone("1234567899");
        myApp.updateAccount("user1@gmail.com",user);
    }

    @Then("the account should be updated")
    public void the_account_should_be_updated() {
        assertEquals(myApp.searchAccount("user1@gmail.com").getFullName(),"new name");
        assertEquals(myApp.searchAccount("user1@gmail.com").getPhone(),"1234567899");
    }

    @When("the admin enter manage user accounts")
    public void the_admin_enter_manage_user_accounts() {
        myApp.state= State.MANAGE_ACCOUNTS;
    }

    @Then("the user accounts should show")
    public void the_user_accounts_should_show() {
        assertEquals(myApp.state,State.MANAGE_ACCOUNTS);
    }

    @When("add new installation appointments")
    public void add_new_installation_appointments() {
        myApp.addAppointment("user1@gmail.com","item1","bmw","10-4-2023");
    }

    @Then("the appointments should be added")
    public void the_appointments_should_be_added() {
        assertNotNull(myApp.searchAppointment(1));
    }

    @When("the admin deletes appointment")
    public void the_admin_deletes_appointment() {
        myApp.deleteAppointment(1);
    }

    @Then("the appointment should be deleted")
    public void the_appointment_should_be_deleted() {
        assertNull(myApp.searchAppointment(1));
    }

    @When("admin update appointment")
    public void admin_update_appointment() {
        Appointment appointment=new Appointment();
        appointment.setDate("2023-10-27");
        myApp.updateAppointment(1,appointment);
    }
    @Then("the appointment should be updated")
    public void the_appointment_should_be_updated() {
        assertEquals(myApp.searchAppointment(1).getDate(),"2023-10-27");
    }
}
