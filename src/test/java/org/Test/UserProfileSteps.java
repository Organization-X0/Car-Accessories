//package org.Test;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.Car.App;
//import org.Data.User;
//
//import static org.junit.Assert.assertEquals;
//
//public class UserProfileSteps {
//    App myApp;
//
//    public UserProfileSteps(App myApp) {
//        this.myApp = myApp;
//    }
//    @Given("a customer is logged into their profile")
//    public void a_customer_is_logged_into_their_profile() {
//        myApp.login("user1@gmail.com","u123");
//        myApp.stateEnum = StateEnum.PROFILE_PAGE;
//    }
//
//    @When("the customer edit their profile")
//    public void the_customer_edit_their_profile() {
//        myApp.handleCustomerProfile("1");
//        assertEquals(myApp.stateEnum, StateEnum.UPDATE_ACCOUNT);
//        User user=new User();
//        user.setFullName("mohammad");
//        myApp.updateAccount("user1@gmail.com",user);
//    }
//
//    @Then("the profile should be edited")
//    public void the_profile_should_be_edited() {
//      assertEquals(myApp.searchAccount("user1@gmail.com").getFullName(),"mohammad");
//    }
//
//    @When("the customer navigates to their order history")
//    public void the_customer_navigates_to_their_order_history() {
//            myApp.handleCustomerProfile("2");
//    }
//
//    @Then("the customer should be able to view all past orders")
//    public void the_customer_should_be_able_to_view_all_past_orders() {
//        assertEquals(myApp.stateEnum, StateEnum.VIEW_ORDERS);
//    }
//
//    @When("the customer navigates to their installation requests")
//    public void the_customer_navigates_to_their_installation_requests() {
//        myApp.handleCustomerProfile("3");
//    }
//
//    @Then("the customer should be able to view all installation requests")
//    public void the_customer_should_be_able_to_view_all_installation_requests() {
//        assertEquals(myApp.stateEnum, StateEnum.VIEW_INSTALLATION_REQ);
//    }
//}