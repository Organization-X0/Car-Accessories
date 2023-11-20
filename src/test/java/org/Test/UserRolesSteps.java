package org.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.Car.App;
import org.Sates.AdminDashboardState;
import org.Sates.CustomerDashboardState;
import org.Sates.InstallerDashboardState;

import static org.junit.Assert.assertTrue;

public class UserRolesSteps {

    App myApp;
    public UserRolesSteps(App myApp){
        this.myApp=myApp;
    }

    @Then("the admin should be able to manage products, categories, and user accounts")
    public void the_admin_should_be_able_to_manage_products_categories_and_user_accounts() {
        assertTrue(myApp.getCurrentState() instanceof AdminDashboardState);
    }

    @Then("the customer should be able to browse products, make purchases, and view orders.")
    public void the_customer_should_be_able_to_browse_products_make_purchases_and_view_orders() {
        assertTrue(myApp.getCurrentState() instanceof CustomerDashboardState);
    }

    @Given("an installer is logged in")
    public void an_installer_is_logged_in() {
        myApp.login("installer@gmail.com","i123");
    }

    @Then("the installer should be able to view installation requests and schedule appointments.")
    public void the_installer_should_be_able_to_view_installation_requests_and_schedule_appointments() {
        assertTrue(myApp.getCurrentState() instanceof InstallerDashboardState);
    }
}
