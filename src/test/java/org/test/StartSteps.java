package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Error;
import org.sates.LoginState;
import org.sates.SignUpState;
import org.sates.StartState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StartSteps {
    App myApp;
    public StartSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("I am on the start page")
    public void i_am_on_the_start_page() {
       assertTrue(myApp.getCurrentState() instanceof StartState);
    }
    @When("I enter {string}")
    public void i_enter(String option) {
        myApp.getCurrentState().handleInput(option);
    }
    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        assertTrue(myApp.getCurrentState() instanceof LoginState);
    }
    @Then("I should be redirected to the signup page")
    public void i_should_be_redirected_to_the_signup_page() {
        assertTrue(myApp.getCurrentState() instanceof SignUpState);
    }
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        assertEquals(myApp.getError().getLocation(),myApp.getCurrentState().getStateString());
    }
    @Then("the App should exit")
    public void the_app_should_exit() {
        assertTrue(myApp.exit);
    }
}
