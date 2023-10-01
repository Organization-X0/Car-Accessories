package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StartSteps {
    App myApp;
    public StartSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("I am on the start page")
    public void i_am_on_the_start_page() {
       assertEquals(myApp.getState(), State.START);
    }
    @When("I enter {string}")
    public void i_enter(String option) {
        myApp.setOption(option);
    }
    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        assertEquals(myApp.state,State.LOGIN);
    }
    @Then("I should be redirected to the signup page")
    public void i_should_be_redirected_to_the_signup_page() {
        assertEquals(myApp.state,State.SIGNUP);
    }
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        assertTrue(myApp.getErrorStart());
    }

}
