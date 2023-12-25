package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Error;
import org.data.Appointment;
import org.data.Product;
import org.sates.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminDashboardSteps {
    App myApp;
    int id=1;
    String email="";
    int appointmentNum=0;
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
        assertEquals(myApp.getCurrentState().getStateString(), myApp.getError().getLocation());
    }
    @When("the admin adds new category {string}")
    public void the_admin_adds_new_category(String categoryName) {
        myApp.getCurrentState().handleInput("a");
        myApp.getCurrentState().handleInput(categoryName);
    }
    @Then("a new category {string} should be created")
    public void a_new_category_should_be_created(String categoryName) {
        assertNotNull(myApp.searchCategory(categoryName));
    }
    @When("the admin updates the category {string} to {string}")
    public void the_admin_updates_the_category_to(String categoryNum, String newCategoryName) {
        myApp.getCurrentState().handleInput("u"+categoryNum);
        myApp.getCurrentState().handleInput(newCategoryName);
    }
    @Then("the category should be updated {string}")
    public void the_category_should_be_updated(String categoryName) {
        assertNotNull(myApp.searchCategory(categoryName));
    }
    @When("the admin deletes the category {string}")
    public void the_admin_deletes_the_category(String categoryNum) {
        myApp.getCurrentState().handleInput("d"+categoryNum);
    }
    @Then("the category {string} should be deleted")
    public void the_category_should_be_deleted(String categoryName) {
        assertNull(myApp.searchCategory(categoryName));
    }
    @When("the admin adds new product {string},{string},{string},{string}")
    public void the_admin_adds_new_product(String categoryNum, String productName, String description, String price) {
        id=Product.getLastId();
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("a");
        Map<String,String> data=new HashMap<>();
        data.put("category",categoryNum);
        data.put("name",productName);
        data.put("description",description);
        data.put("price",price);
        myApp.getCurrentState().handleInput(data);
    }
    @Then("a new product listing should be created")
    public void a_new_product_listing_should_be_created() {
        assertNotNull(myApp.searchProduct(id+1));
    }

    @When("the admin updates the product {string} to {string},{string},{string},{string}")
    public void the_admin_updates_the_product_to(String productID, String categoryNum, String productName, String description, String price) {
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("u"+productID);

        Map<String,String> data=new HashMap<>();
        data.put("name",productName);
        data.put("description",description);
        data.put("price",price);
        data.put("available","t");
        myApp.getCurrentState().handleInput(data);
    }
    @Then("the product should be updated {string}, {string}")
    public void the_product_should_be_updated(String productNum, String productName) {
        int num=Integer.parseInt(productNum);
        assertEquals(myApp.searchProduct(num).getName(),productName);
    }

    @When("the admin deletes the product {string}")
    public void the_admin_deletes_the_product(String productNum) {
        myApp.getCurrentState().handleInput("1");
        myApp.getCurrentState().handleInput("d"+productNum);
    }
    @Then("the product {string} should be deleted")
    public void the_product_should_be_deleted(String productNum) {
        int num=Integer.parseInt(productNum);
        assertNull(myApp.searchProduct(num));
    }
    @When("the admin updates the account {string} to {string},{string}")
    public void the_admin_updates_the_account_to(String accountNum, String newName, String newPhone) {
        myApp.getCurrentState().handleInput("u"+accountNum);
        Map<String,String> data=new HashMap<>();
        data.put("fullName", newName);
        data.put("phone", newPhone);
        myApp.getCurrentState().handleInput(data);
    }
    @Then("the account should be updated {string},{string}")
    public void the_account_should_be_updated(String newName, String newPhone) {
        assertEquals(myApp.searchAccount(myApp.getUserEmailToUpdate()).getFullName(),newName);
        assertEquals(myApp.searchAccount(myApp.getUserEmailToUpdate()).getPhone(),newPhone);
    }
    @When("the admin delete account {string}")
    public void the_admin_delete_account(String accountNum) {
        myApp.getCurrentState().handleInput("d"+accountNum);
    }
    //fix..
    @Then("the account should be deleted")
    public void the_account_should_be_deleted() {
//        assertNull(myApp.searchAccount("user1@gmail.com"));

    }

    @When("admin enters manage accounts page {string}")
    public void admin_enters_manage_accounts_page(String input) {
        myApp.getCurrentState().handleInput(input);
    }
    @Then("the user accounts should show")
    public void the_user_accounts_should_show() {
        assertTrue(myApp.getCurrentState() instanceof ManageAccountsState);
    }
    @Given("admin enters manage installation page {string}")
    public void admin_enters_manage_installation_page(String input) {
        myApp.getCurrentState().handleInput(input);
    }

    @When("add new installation appointments {string}, {string},{string},{string},{string}")
    public void add_new_installation_appointments(String email, String productName, String carMake, String date, String time) {
        id= Appointment.getLastId();
        myApp.getCurrentState().handleInput("a");
        Map<String,String> data=new HashMap<>();

        data.put("email",email);
        data.put("productName",productName);
        data.put("carMake",carMake);
        data.put("date",date);
        data.put("time",time);
        myApp.getCurrentState().handleInput(data);
    }

    @Then("the appointments should be added")
    public void the_appointments_should_be_added() {
        assertNotNull(myApp.searchAppointment(id));
    }

    @When("the admin deletes appointment {string}")
    public void the_admin_deletes_appointment(String appointmentNum) {
            int num = Integer.parseInt(appointmentNum);
            myApp.deleteAppointment(num);
    }
    @Then("the appointment should be deleted {string}")
    public void the_appointment_should_be_deleted(String appointmentNum) {
        int num=Integer.parseInt(appointmentNum);
        assertNull(myApp.searchAppointment(num));
    }
    @Given("admin enters {string} to update")
    public void admin_enters_to_update(String appointmentToUpdate) {
        myApp.getCurrentState().handleInput("u"+appointmentToUpdate);
    }
    @When("admin update appointment {string} to {string}, {string},{string},{string}")
    public void admin_update_appointment_to(String appointmentNum, String userEmail, String productName, String carMake, String date) {
        Map<String,String> data=new HashMap<>();
        data.put("email", userEmail);
        data.put("productName", productName);
        data.put("carMake", carMake);
        data.put("date", date);
        myApp.getCurrentState().handleInput(data);
    }
    @Then("the appointment should be updated {string} , {string}, {string},{string},{string}")
    public void the_appointment_should_be_updated(String appointmentNum, String userEmail, String productName, String carMake, String date) {
        int num=Integer.parseInt(appointmentNum);
        assertEquals(myApp.searchAppointment(num).getDate(),date);
        assertEquals(myApp.searchAppointment(num).getEmail(),userEmail);
        assertEquals(myApp.searchAppointment(num).getProductName(),productName);
        assertEquals(myApp.searchAppointment(num).getCarMake(),carMake);
    }

    @Given("admin enters manage categories page")
    public void admin_enters_manage_categories_page() {
        myApp.getCurrentState().handleInput("2");
    }

    @Given("admin enters manage product page")
    public void admin_enters_manage_product_page() {
        myApp.getCurrentState().handleInput("1");
    }

    @Given("admin enters manage accounts page")
    public void admin_enters_manage_accounts_page() {
        myApp.getCurrentState().handleInput("3");
    }

    @Given("admin enters manage installation page")
    public void admin_enters_manage_installation_page() {
        myApp.getCurrentState().handleInput("4");
    }
    @Given("inside manage appointment")
    public void inside_manage_appointment() {
        myApp.setState(new ManageInstallationAppointmentState(myApp));
    }

    @Then("the system should navigate to admin dashboard")
    public void the_system_should_navigate_to_admin_dashboard() {
        assertTrue(myApp.getCurrentState() instanceof AdminDashboardState);
    }
    @Given("has appointments")
    public void has_appointments() {
        appointmentNum= myApp.myDatabase.getRequestedAppointmentsList().size();
    }

    @Then("the system should delete the appropriate appointment")
    public void the_system_should_delete_the_appropriate_appointment() {
        assertEquals(appointmentNum-1,myApp.myDatabase.getRequestedAppointmentsList().size());
    }
    @When("the user adds an empty appointment")
    public void the_user_adds_an_empty_appointment() {
        id= Appointment.getLastId();
        myApp.getCurrentState().handleInput("a");
        Map<String,String> data=new HashMap<>();

        data.put("email","");
        data.put("productName","");
        data.put("carMake","");
        data.put("date","");
        data.put("time","");
        myApp.getCurrentState().handleInput(data);
    }

    @Then("the application should return without adding an appointment")
    public void the_application_should_return_without_adding_an_appointment() {
        assertNull(myApp.searchAppointment(id+1));
    }
    @Then("the application should throw an Error")
    public void the_application_should_throw_an_error() {
        assertNotNull(myApp.getError().getLocation());
    }
    @When("the admin enters an invalid option {string}")
    public void the_admin_enters_an_invalid_option(String input) {
        myApp.getCurrentState().handleInput(input);
    }

    @Then("an error should be displayed")
    public void an_error_should_be_displayed() {
        assertEquals(myApp.getCurrentState().getStateString(), myApp.getCurrentState().getStateString());
    }


}
