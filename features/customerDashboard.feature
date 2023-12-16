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

Feature: Make purchases

  Scenario Outline: Make purchases
    Given a customer is logged in
    And the customer enters the product catalog page and chooses the "<categoryNum>"
    When the customer buys "<product>" from the product catalog
    Then the purchase should be completed
    Examples:
      | categoryNum | product |
      | 1            | 5       |
      | 3            | 2       |
      | 4            | 3       |

  Scenario: View orders history
    Given an customer is logged in
    And customer enter profile page "3"
    When enter show orders history "2"
    Then the orders history should be shown