package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.Appointment;
import org.Data.Product;
import org.Data.User;
import org.Sates.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminDashboardSteps {
    App myApp;
    int id=1;
    public AdminDashboardSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        myApp.login("admin@gmail.com","a123");
    }

    @When("the admin enter {string}")
    public void the_admin_enter(String input) {
       myApp.getCurrentState().handleInput(input);
    }

    @Then("should be redirected to the Manage Products")
    public void should_be_redirected_to_the_manage_products() {
        assertTrue(myApp.getCurrentState() instanceof ManageProductsState);
    }

    @Then("should be redirected to the Manage Categories")
    public void should_be_redirected_to_the_manage_categories() {
        assertTrue(myApp.getCurrentState() instanceof ManageCategoriesState);
    }
    @Then("should be redirected to the Manage User Accounts")
    public void should_be_redirected_to_the_manage_user_accounts() {

        assertTrue(myApp.getCurrentState() instanceof ManageAccountsState);
    }
    @Then("should be redirected to the Manage Installation Appointments")
    public void should_be_redirected_to_the_manage_installation_appointments() {

        assertTrue(myApp.getCurrentState() instanceof ManageInstallationAppointmentState);
    }
    @Then("should be redirected to the Log out")
    public void should_be_redirected_to_the_log_out() {
        assertTrue(myApp.getCurrentState() instanceof StartState);

    }
    @Then("should see an error message on Admin Dashboard")
    public void should_see_an_error_message_on_admin_dashboard() {
        assertEquals(myApp.getCurrentState().getStateString(), Error.getLocation());
    }
    @Given("admin enters manage categories page")
    public void admin_enters_manage_categories_page() {
        myApp.getCurrentState().handleInput("2");
    }
    @When("the admin adds new category")
    public void the_admin_adds_new_category() {
        myApp.getCurrentState().handleInput("a");
        myApp.getCurrentState().handleInput("new category");
    }

    @Then("a new category should be created")
    public void a_new_category_should_be_created() {
        assertNotNull(myApp.searchCategory("new category"));
    }

    @When("the admin updates the category")
    public void the_admin_updates_the_category() {
        myApp.getCurrentState().handleInput("u1");
        myApp.getCurrentState().handleInput("new category");
    }

    @Then("the category should be updated")
    public void the_category_should_be_updated() {
        assertNotNull(myApp.searchCategory("new category"));
    }

    @When("the admin delete category")
    public void the_admin_delete_category() {
        myApp.getCurrentState().handleInput("d1");
    }
    @Then("the category should be deleted")
    public void the_category_should_be_deleted() {
        assertNull(myApp.searchCategory("Interior"));
    }

    @Given("admin enters manage product page")
    public void admin_enters_manage_product_page() {
        myApp.getCurrentState().handleInput("1");
    }
    @When("the admin adds new product")
    public void the_admin_adds_new_product() {
        id=Product.getLastId();
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("a");
        Map<String,String> data=new HashMap<>();
        data.put("category","1");
        data.put("name","itemX");
        data.put("description","about this item..");
        data.put("price","4.5");
        myApp.getCurrentState().handleInput(data);
    }

    @Then("a new product listing should be created")
    public void a_new_product_listing_should_be_created() {
        assertNotNull(myApp.searchProduct(id+1));
    }

    @When("the admin updates the product")
    public void the_admin_updates_the_product() {
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("u1");

        Map<String,String> data=new HashMap<>();
        data.put("name","item y");
        data.put("description","about this y");
        data.put("price","11");
        myApp.getCurrentState().handleInput(data);
    }

    @Then("the product should be updated")
    public void the_product_should_be_updated() {
        assertEquals(myApp.searchProduct(1).getName(),"item y");
    }
    @When("the admin delete product")
    public void the_admin_delete_product() {
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("d1");
    }

    @Then("the product should be deleted")
    public void the_product_should_be_deleted() {
        assertNull(myApp.searchProduct(1));
    }

    @Given("admin enters manage accounts page")
    public void admin_enters_manage_accounts_page() {
        myApp.getCurrentState().handleInput("3");
    }
    @When("the admin delete account")
    public void the_admin_delete_account() {
        myApp.getCurrentState().handleInput("d1");
    }

    @Then("the account should be deleted")
    public void the_account_should_be_deleted() {
       assertNull(myApp.searchAccount("user1@gmail.com"));
    }

    @When("the admin update account")
    public void the_admin_update_account() {
        myApp.getCurrentState().handleInput("u1");
        Map<String,String> data=new HashMap<>();
        data.put("fullName", "new name");
        data.put("phone", "1234567899");
        myApp.getCurrentState().handleInput(data);
    }

    @Then("the account should be updated")
    public void the_account_should_be_updated() {
        assertEquals(myApp.searchAccount("user1@gmail.com").getFullName(),"new name");
        assertEquals(myApp.searchAccount("user1@gmail.com").getPhone(),"1234567899");
    }

    @When("the admin enter manage user accounts")
    public void the_admin_enter_manage_user_accounts() {
        myApp.setState(new ManageAccountsState(myApp));
    }

    @Then("the user accounts should show")
    public void the_user_accounts_should_show() {
        assertTrue(myApp.getCurrentState() instanceof ManageAccountsState);
    }

    @Given("admin enters manage installation page")
    public void admin_enters_manage_installation_page() {
        myApp.getCurrentState().handleInput("4");
    }

    @When("add new installation appointments")
    public void add_new_installation_appointments() {
        myApp.getCurrentState().handleInput("a");
        Map<String,String> data=new HashMap<>();
        data.put("email","user1gmail.com");
        data.put("productName","item1");
        data.put("carMake","bmw");
        data.put("date","10-4-2023");
        myApp.getCurrentState().handleInput(data);
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
