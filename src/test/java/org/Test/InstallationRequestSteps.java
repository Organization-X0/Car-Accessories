package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Data.Appointment;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class InstallationRequestSteps {
    App myApp;
    int id;
    public InstallationRequestSteps(App myApp){
       this.myApp=myApp;
       id=1;
    }

    @Given("customer is logged in and requesting installation services")
    public void customer_is_logged_in_and_requesting_installation_services() {
        myApp.getCurrentState().handleInput("1");

        Map<String,String> data=new HashMap<>();
        data.put("email","user1@gmail.com");
        data.put("password","u123");

        myApp.getCurrentState().handleInput(data);
        myApp.getCurrentState().handleInput("2");
    }

    @When("customer fill in the installation request form")
    public void customer_fill_in_the_installation_request_form() {
        id= Appointment.getLastId();
        Map<String,String> data=new HashMap<>();
        data.put("email",myApp.email);
        data.put("productName","item1");
        data.put("carMake","bmw");
        data.put("date","2023-11-8");
        myApp.getCurrentState().handleInput(data);
    }

    @Then("customer installation request should be submitted")
    public void customer_installation_request_should_be_submitted() {
        assertNotNull(myApp.searchAppointment(id));
    }
}
