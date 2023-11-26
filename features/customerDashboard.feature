Feature: Customer Dashboard

  Scenario: Customer Dashboard page to Product Catalog
    Given an customer is logged in
    When the customer enter "1"
    Then should be redirected to the Product Catalog

  Scenario: Customer Dashboard page to Request Services
    Given an customer is logged in
    When the customer enter "2"
    Then should be redirected to the Request Services

  Scenario: Customer Dashboard page to Profile Page
    Given an customer is logged in
    When the customer enter "3"
    Then should be redirected to Profile Page

  Scenario: Customer Dashboard page to Notification Center
    Given an customer is logged in
    When the customer enter "4"
    Then should be redirected to the Notification Center

  Scenario: Customer Dashboard page to Log out
    Given an customer is logged in
    When the customer enter "5"
    Then should be redirected to the Log out

  Scenario Outline: Invalid input customer dashboard
    Given an customer is logged in
    When the customer enter "<input>"
    Then  should see an error message on customer Dashboard
    Examples:
      | input |
      | 7     |
      | g     |
      | abc   |
      | -1    |

  Scenario: Make purchases
    Given an customer is logged in
    And customer enter product catalog page and choose category
    When customer buys product from product catalog
    Then the purchase should be completed


  Scenario: View orders history
    Given an customer is logged in
    And customer enter profile page
    When enter show orders history
    Then the orders history should be shown