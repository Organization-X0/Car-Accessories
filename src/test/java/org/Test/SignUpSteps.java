package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.Error;
import org.Car.StateEnum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignUpSteps {
    App myApp;
    public SignUpSteps(App myApp){
        this.myApp=myApp;
    }
    @Given("on the sign up page")
    public void on_the_sign_up_page() {
        myApp.stateEnum = StateEnum.SIGNUP;
        assertEquals(StateEnum.SIGNUP,myApp.getStateEnum());
    }
    @When("valid details are entered")
    public void valid_details_are_entered() {
        myApp.signUp( "userx","abod@gmail.com","1234567890","123456aa");
    }
    @Then("go to the login page")
    public void go_to_the_login_page() {
        assertEquals(StateEnum.LOGIN,myApp.getStateEnum());
    }

    @When("invalid details or missing information are entered")
    public void invalid_details_or_missing_information_are_entered() {
        myApp.signUp( "12315234544156","","123","0568345816");
    }
    @Then("Show error message indicates what went wrong")
    public void show_error_message_indicates_what_went_wrong() {
       assertEquals(Error.getLocation(), StateEnum.SIGNUP);
    }
}
