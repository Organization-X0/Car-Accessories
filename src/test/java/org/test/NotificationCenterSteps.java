package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.sates.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NotificationCenterSteps {
    App myApp;
    int pageNow;
    Map<String,String> data;
    User account;
    int notiNum;
    public NotificationCenterSteps(App myApp){
        this.myApp=myApp;
        pageNow=0;
        data=new HashMap<>();
        notiNum=0;
    }

    @Given("inside notification center")
    public void inside_notification_center() {
        myApp.setState(new NotificationCenterState(myApp));
    }
    @Given("the current page is not the total pages")
    public void the_current_page_is_not_the_total_pages() {
        Cli.setTotalPages(5);
        Cli.setPage(2);
        pageNow=Cli.getCurrentPage();
    }

    @When("the input is {string}")
    public void the_input_is(String input) {
        myApp.getCurrentState().handleInput(input);
    }

    @Then("the system should navigate to the next page")
    public void the_system_should_navigate_to_the_next_page() {
        assertEquals(pageNow+1,Cli.getCurrentPage());
    }

    @Given("the current page is not the first page")
    public void the_current_page_is_not_the_first_page() {
        Cli.setTotalPages(5);
        Cli.setPage(3);
        pageNow=Cli.getCurrentPage();
    }

    @Then("the system should navigate to the previous page")
    public void the_system_should_navigate_to_the_previous_page() {
        assertEquals(pageNow-1,Cli.getCurrentPage());
    }
    @Given("inside notification center And a installer is logged in")
    public void inside_notification_center_and_a_installer_is_logged_in() {
        myApp.setState(new LoginState(myApp));
        data.put("email","installer@gmail.com");
        data.put("password","i123");
        myApp.getCurrentState().handleInput(data);
        myApp.getCurrentState().handleInput("3");
    }
    @Given("inside notification center And a customer is logged in")
    public void inside_notification_center_and_a_customer_is_logged_in() {
        myApp.setState(new LoginState(myApp));
        data.put("email","user1@gmail.com");
        data.put("password","u123");
        myApp.getCurrentState().handleInput(data);
        myApp.getCurrentState().handleInput("4");
    }

    @Then("the system should navigate to the installer dashboard")
    public void the_system_should_navigate_to_the_installer_dashboard() {
        assertTrue(myApp.getCurrentState() instanceof InstallerDashboardState);
    }
    @Then("the system should navigate to customer dashboard")
    public void the_system_should_navigate_to_customer_dashboard() {
        assertTrue(myApp.getCurrentState() instanceof CustomerDashboardState);
    }

    @Given("has notifications")
    public void has_notifications() {
        account=myApp.searchAccount(myApp.email);
        notiNum= account.getNotificationCount();
        account.pushNotification("You bought this product \""+Cli.blueText("test-item")+"\" successfully.");
        account.increaseNotificationCount();
    }

    @Then("the system should delete the appropriate notification")
    public void the_system_should_delete_the_appropriate_notification() {
        assertEquals(notiNum+1,account.getNotificationCount());
    }

    @Then("the system should set an error")
    public void the_system_should_set_an_error() {
        assertNotNull(Error.getLocation());
    }
}
