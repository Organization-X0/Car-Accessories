Feature: User Profile

  Scenario: Customer can edit their profile
    Given a customer is logged into their profile
    When the customer edit their profile
    Then the profile should be edited

  Scenario: Customers can view order history
    Given a customer is logged into their profile
    When the customer navigates to their order history
    Then the customer should be able to view all past orders

  Scenario: Customers can view installation requests
    Given a customer is logged into their profile
    When the customer navigates to their installation requests
    Then the customer should be able to view all installation requests
