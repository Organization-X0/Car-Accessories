package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.Error;
import org.Sates.AdminDashboardState;
import org.Sates.CustomerDashboardState;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginSteps {
    App myApp;
    Map<String,String> data;
    public LoginSteps(App myApp){
        this.myApp=myApp;
        data=new HashMap<>();
    }
    @Given("on the login page")
    public void on_the_login_page() {
        myApp.getCurrentState().handleInput("1");
    }
    @When("valid username and password are entered")
    public void valid_username_and_password_are_entered() {

        data.put("email","user1@gmail.com");
        data.put("password","u123");
        myApp.getCurrentState().handleInput(data);
    }
    @Then("redirection to the main page should occur")
    public void redirection_to_the_main_page_should_occur() {
if (myApp.whoLoggedIn().equals("admin")){
    assertTrue(myApp.getCurrentState() instanceof AdminDashboardState);

} else if (myApp.whoLoggedIn().equals("customer")) {
    assertTrue(myApp.getCurrentState() instanceof CustomerDashboardState);

}

    }

    @When("invalid username or password are entered")
    public void invalid_username_or_password_are_entered() {

        data.put("email","ab@gmail.com");
        data.put("password","u14423");

        myApp.getCurrentState().handleInput(data);
    }
    @Then("an error message should be seen")
    public void an_error_message_should_be_seen() {
        assertEquals(Error.getLocation(), myApp.getCurrentState().getStateString());
    }

}
