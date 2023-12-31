package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Error;
import org.sates.AdminDashboardState;
import org.sates.CustomerDashboardState;

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
        assertEquals(myApp.getError().getLocation(), myApp.getCurrentState().getStateString());
    }

    @When("input of invalid type is entered")
    public void inputOfInvalidTypeIsEntered() {
        myApp.getCurrentState().handleInput("test");
    }
}
