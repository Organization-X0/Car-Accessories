package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Error;
import org.sates.LoginState;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignUpSteps {
    App myApp;
    Map<String,String>data;
    public SignUpSteps(App myApp){
        this.myApp=myApp;
       data=new HashMap<>();
    }
    @Given("on the sign up page")
    public void on_the_sign_up_page() {
        myApp.getCurrentState().handleInput("2");
    }
    @When("valid details are entered")
    public void valid_details_are_entered() {

        data.put("fullName","userx");

        data.put("email","abod@gmail.com");

        data.put("phone","1234567890");

        data.put("password","123456aa");

        myApp.getCurrentState().handleInput(data);

    }
    @Then("go to the login page")
    public void go_to_the_login_page() {
        assertTrue(myApp.getCurrentState() instanceof LoginState);
    }

    @When("invalid details or missing information are entered")
    public void invalid_details_or_missing_information_are_entered() {
        data.put("fullName","12345648484");

        data.put("email","");

        data.put("phone","1234567");

        data.put("password","123412121");

        myApp.getCurrentState().handleInput(data);

    }
    @Then("Show error message indicates what went wrong")
    public void show_error_message_indicates_what_went_wrong() {
       assertEquals(Error.getLocation(), myApp.getCurrentState().getStateString());
    }
}
