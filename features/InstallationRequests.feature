Feature: Installation Requests

  Scenario Outline: Customer request installation service
    Given a customer is logged in and requesting installation services "2"
    When the customer fills in the installation request form for service with "<product_name>", "<car_make>","<date>" and "<time_slot>"
    Then the customer should see the available time slots for service
    And the customer chooses the "<time_slot>" for the service
    Then the customer's installation request for  should be submitted

    Examples:
       | product_name | car_make | date      | time_slot |
       | Air Conditioner | Toyota | 2023-12-01 | 1 |
       | Sink           | Honda  | 2023-12-02  | 2 |
       | Lights          | Ford   |2023-12-03  | 3 |

