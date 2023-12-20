package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.data.Appointment;
import org.sates.InstallationRequestsState;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InstallationRequestSteps {
    App myApp;
    int id;
    public InstallationRequestSteps(App myApp){
       this.myApp=myApp;
       id=1;
    }
    @Given("inside installation requests page")
    public void inside_installation_requests_page() {
        myApp.setState(new InstallationRequestsState(myApp));
    }
    @When("the admin chooses to confirm appointment {string}")
    public void the_admin_chooses_to_confirm_appointment(String input) {
        myApp.getCurrentState().handleInput("c"+input);
    }
    @Then("the appointment {string} should be confirmed")
    public void the_appointment_should_be_confirmed(String input) {
        assertNotNull(myApp.myDatabase.searchApprovedAppointment(Integer.parseInt(input)));
    }
    @Then("an error should be shown")
    public void an_error_should_be_shown() {
        assertNotEquals("Null", myApp.error.getLocation());
    }

}
