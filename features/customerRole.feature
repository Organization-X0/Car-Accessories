Feature: Customer Role

  Scenario: Browse Products
    Given a customer is logged in
    When the customer browses products
    Then the products should be displayed

  Scenario: Make Purchases
    Given a customer is logged in and a product is available for purchase
    When the customer makes a purchase
    Then the purchase should be successful

  Scenario: View Orders
    Given a customer is logged in and has made purchases
    When the customer views orders
    Then the orders should be displayed