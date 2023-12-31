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

  Scenario Outline: Make purchases
    Given an customer is logged in
    And the customer enters the product catalog page and chooses the "<categoryNum>"
    When the customer buys "<product>" from the product catalog
    Then the purchase should be completed
    Examples:
      | categoryNum | product |
      | 1            | 5       |
      | 3            | 1       |
      | 4            | 3       |

  Scenario Outline: Attempt to purchase an unavailable product
    Given an customer is logged in
    And the customer enters the product catalog page and chooses the "<categoryNum>"
    When the customer buys "<product>" from the product catalog
    Then the purchase should not be completed
    Examples:
      | categoryNum | product |
      | 1            | 2       |

  Scenario Outline: Handle invalid product number
    Given an customer is logged in
    And the customer enters the product catalog page and chooses the "<categoryNum>"
    When the customer buys "<product>" from the product catalog
    Then an error should be shown
    Examples:
      | categoryNum | product |
      | 1            | 50       |
      | 3            | 260       |
      | 4            | 300       |

  Scenario: View orders history
    Given an customer is logged in
    And customer enter profile page "3"
    When enter show orders history "2"
    Then the orders history should be shown

  Scenario Outline: Customer request installation service
    Given an customer is logged in
    And customer requesting installation services page "2"
    When the customer fills in the installation request form for service with "<product_name>", "<car_make>","<date>" and "<time_slot>"
    Then the customer should see the available time slots for service
    And the customer chooses the "<time_slot>" for the service
    Then the customer's installation request for  should be submitted

    Examples:
      | product_name | car_make | date      | time_slot |
      | Air Conditioner | Toyota | 2023-12-01 | 1 |
      | Sink           | Honda  | 2023-12-02  | 2 |
      | Lights          | Ford   |2023-12-03  | 3 |


  Scenario: Handle View installation history
    Given inside view installation history
    And the current page is not the total pages
    When the input is "n"
    Then the system should navigate to the next page

  Scenario: Handle View installation history
    Given inside view installation history
    And the current page is not the first page
    When the input is "p"
    Then the system should navigate to the previous page

  Scenario: Handle View installation history
    Given inside view installation history
    When the input is "b"
    Then the system should navigate to Profile

  Scenario: Handle View order history
    Given inside view order history
    And the current page is not the total pages
    When the input is "n"
    Then the system should navigate to the next page

  Scenario: Handle View order history
    Given inside view order history
    And the current page is not the first page
    When the input is "p"
    Then the system should navigate to the previous page

  Scenario: Handle View order history
    Given inside view order history
    When the input is "b"
    Then the system should navigate to Profile
