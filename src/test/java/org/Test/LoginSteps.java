package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.Error;
import org.Car.StateEnum;

import static org.junit.Assert.*;

public class LoginSteps {
    App myApp;
    public LoginSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("on the login page")
    public void on_the_login_page() {
        assertFalse(myApp.loggedIn);
    }
    @When("valid username and password are entered")
    public void valid_username_and_password_are_entered() {
        myApp.login("user1@gmail.com","u123");
    }
    @Then("redirection to the main page should occur")
    public void redirection_to_the_main_page_should_occur() {
//        assertTrue(myApp.getCurrentState() instanceof CustomerDashboardState);

    }

    @When("invalid username or password are entered")
    public void invalid_username_or_password_are_entered() {
        myApp.login("AboGhalb@gmail.com","balela");
    }
    @Then("an error message should be seen")
    public void an_error_message_should_be_seen() {
        assertEquals(Error.getLocation(), myApp.getCurrentState().getStateString());
    }

}
