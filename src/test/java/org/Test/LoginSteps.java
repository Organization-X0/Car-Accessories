package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.State;

import static org.junit.Assert.*;

public class LoginSteps {
    App myApp = new App();
    @Given("on the login page")
    public void on_the_login_page() {
        assertFalse(myApp.loggedIn);
    }
    @When("valid username and password are entered")
    public void valid_username_and_password_are_entered() {
        myApp.login("user1","123");
    }
    @Then("redirection to the main page should occur")
    public void redirection_to_the_main_page_should_occur() {
        assertEquals(State.MAIN, myApp.getState());
    }

    @When("invalid username or password are entered")
    public void invalid_username_or_password_are_entered() {
        myApp.login("AboGhalb","balela");
    }
    @Then("an error message should be seen")
    public void an_error_message_should_be_seen() {
        assertTrue(myApp.errorDisplayedLogin());
    }

}
