package org.test;

import org.car.App;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.Error;
import org.data.Appointment;
import org.sates.*;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CustomerDashboardSteps {
    App myApp;
    int size;
    int id;
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
        assertEquals(myApp.getCurrentState().getStateString(), myApp.getError().getLocation());
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
        size=myApp.searchAccount(myApp.getEmail()).getOrders().size();

    }
    @When("the customer buys {string} from the product catalog")
    public void the_customer_buys_from_the_product_catalog(String product) {
        myApp.getCurrentState().handleInput("f"+product);
    }
    @Then("the purchase should be completed")
    public void the_purchase_should_be_completed() {
        assertEquals(myApp.searchAccount(myApp.getEmail()).getOrders().size(),size+1);
    }
    @Given("customer enter profile page {string}")
    public void customer_enter_profile_page(String option) {

        myApp.getCurrentState().handleInput(option);
    }

    @When("enter show orders history {string}")
    public void enter_show_orders_history(String option) {
        myApp.getCurrentState().handleInput(option);
    }

    @Given("customer requesting installation services page {string}")
    public void customer_requesting_installation_services_page(String input) {
        myApp.getCurrentState().handleInput(input);
    }
    @When("the customer fills in the installation request form for service with {string}, {string}, and {string}")
    public void the_customer_fills_in_the_installation_request_form_for_service_with_and(String product_name, String car_make, String date) {

    }
    @When("the customer fills in the installation request form for service with {string}, {string},{string} and {string}")
    public void the_customer_fills_in_the_installation_request_form_for_service_with_and(String product_name, String car_make, String date, String time_slot) {
        id= Appointment.getLastId();
        Map<String,String> data=new HashMap<>();
        data.put("email",myApp.getEmail());
        data.put("productName",product_name);
        data.put("carMake",car_make);
        data.put("date",date);
        data.put("time",time_slot);
        myApp.getCurrentState().handleInput(data);
    }
    @Then("the customer should see the available time slots for service")
    public void the_customer_should_see_the_available_time_slots_for_service() {
        assertTrue(myApp.isAvailableTimesShown());
    }
    @Then("the customer chooses the {string} for the service")
    public void the_customer_chooses_the_for_the_service(String time_slot) {
        myApp.getCurrentState().handleInput(time_slot);
    }
    @Then("the customer's installation request for  should be submitted")
    public void the_customer_s_installation_request_for_should_be_submitted() {
        assertNotNull(myApp.searchAppointment(id));
    }

    @Then("the orders history should be shown")
    public void the_orders_history_should_be_shown() {
        assertTrue(myApp.getCurrentState() instanceof ViewOrderHistoryState);
    }
    @Given("inside view installation history")
    public void inside_view_installation_history() {
        myApp.setState(new ViewInstallationHistoryState(myApp));
    }
    @Then("the system should navigate to Profile")
    public void the_system_should_navigate_to_profile() {
        assertTrue(myApp.getCurrentState() instanceof ProfileState);
    }
    @Given("inside view order history")
    public void inside_view_order_history() {
        myApp.setState(new ViewOrderHistoryState(myApp));
    }
}
