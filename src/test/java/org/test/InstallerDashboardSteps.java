package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Error;
import org.sates.InstallationRequestsState;
import org.sates.NotificationCenterState;
import org.sates.ScheduleOfAppointmentsState;
import org.sates.StartState;

import static org.junit.Assert.*;

public class InstallerDashboardSteps {
    App myApp;
    public InstallerDashboardSteps(App myApp){
        this.myApp=myApp;
    }
    @When("the installer enter {string}")
    public void the_installer_enter(String string) {
        myApp.getCurrentState().handleInput(string);
    }
    @Given("installer enters Schedule of Appointments page")
    public void installer_enters_schedule_of_appointments_page() {
        myApp.getCurrentState().handleInput("1");
    }

    @When("the installer confirm the Appointment")
    public void the_installer_confirm_the_appointment() {
        myApp.getCurrentState().handleInput("d1");
    }
    @Then("the appointment should be deleted from Schedule  Appointments")
    public void the_appointment_should_be_deleted_from_schedule_appointments() {
        assertNull(myApp.searchApprovedAppointment(1));
    }
    @Then("should be redirected to the Schedule of Appointments page")
    public void should_be_redirected_to_the_schedule_of_appointments_page() {
        assertTrue(myApp.getCurrentState() instanceof ScheduleOfAppointmentsState);
    }
    @Then("should be redirected to the Notification Center page")
    public void should_be_redirected_to_the_notification_center_page() {
        assertTrue(myApp.getCurrentState() instanceof NotificationCenterState);
    }

    @Then("should be redirected to the Installation requests page")
    public void should_be_redirected_to_the_installation_requests_page() {
       assertTrue(myApp.getCurrentState() instanceof InstallationRequestsState);
    }

    @Then("should be redirected to the Log out page")
    public void should_be_redirected_to_the_log_out_page() {
        assertTrue(myApp.getCurrentState() instanceof StartState);
    }

    @Then("should see an error message on installer Dashboard")
    public void should_see_an_error_message_on_installer_dashboard() {
        assertEquals(myApp.getCurrentState().getStateString(), myApp.getError().getLocation());
    }

}
