package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.data.Appointment;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InstallationRequestSteps {
    App myApp;
    int id;
    public InstallationRequestSteps(App myApp){
       this.myApp=myApp;
       id=1;
    }


    @Given("a customer is logged in and requesting installation services {string}")
    public void a_customer_is_logged_in_and_requesting_installation_services(String option) {
        myApp.getCurrentState().handleInput("1");

        Map<String,String> data=new HashMap<>();
        data.put("email","user1@gmail.com");
        data.put("password","u123");

        myApp.getCurrentState().handleInput(data);
        myApp.getCurrentState().handleInput(option);
    }
    @When("the customer fills in the installation request form for service with {string}, {string}, and {string}")
    public void the_customer_fills_in_the_installation_request_form_for_service_with_and(String product_name, String car_make, String date) {

    }
    @When("the customer fills in the installation request form for service with {string}, {string},{string} and {string}")
    public void the_customer_fills_in_the_installation_request_form_for_service_with_and(String product_name, String car_make, String date, String time_slot) {
        id= Appointment.getLastId();
        Map<String,String> data=new HashMap<>();
        data.put("email",myApp.email);
        data.put("productName",product_name);
        data.put("carMake",car_make);
        data.put("date",date);
        data.put("time",time_slot);
        myApp.getCurrentState().handleInput(data);
    }
    @Then("the customer should see the available time slots for service")
    public void the_customer_should_see_the_available_time_slots_for_service() {
        assertTrue(myApp.availableTimesShown);
    }
    @Then("the customer chooses the {string} for the service")
    public void the_customer_chooses_the_for_the_service(String time_slot) {
        myApp.getCurrentState().handleInput(time_slot);

    }
    @Then("the customer's installation request for  should be submitted")
    public void the_customer_s_installation_request_for_should_be_submitted() {
        assertNotNull(myApp.searchAppointment(id));
    }

}
