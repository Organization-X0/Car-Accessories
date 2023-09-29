package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Car.App;
import org.Car.SignUp;
import org.Car.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignUpSteps {
    App myApp=new App();
    @Given("on the sign up page")
    public void on_the_sign_up_page() {
        myApp.state= State.SIGNUP;
        assertEquals(State.SIGNUP,myApp.getState());
    }
    @When("valid details are entered")
    public void valid_details_are_entered() {
        // Write code here that turns the phrase above into concrete actions
        myApp.signUp( "userx","abood@gmail.com","1234567890","123456aa");
    }
    @Then("go to the login page")
    public void go_to_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(State.LOGIN,myApp.getState());
    }

    @When("invalid details or missing information are entered")
    public void invalid_details_or_missing_information_are_entered() {
        // Write code here that turns the phrase above into concrete actions
        myApp.signUp( "12315234544156","","123","0568345816");
    }
    @Then("Show error message indicates what went wrong")
    public void show_error_message_indicates_what_went_wrong() {
        // Write code here that turns the phrase above into concrete actions
       assertTrue(myApp.errorDisplayedSignUp());
    }
}
