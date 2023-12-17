Feature: Customer Dashboard

  Scenario: Test next page navigation
    Given inside notification center
    And the current page is not the total pages
    When the input is "n"
    Then the system should navigate to the next page

  Scenario: Test previous page navigation
    Given inside notification center
    And the current page is not the first page
    When the input is "p"
    Then the system should navigate to the previous page

  Scenario: Test dashboard navigation
    Given inside notification center And a customer is logged in
    When the input is "b"
    Then the system should navigate to customer dashboard

  Scenario: Test dashboard navigation
    Given inside notification center And a installer is logged in
    When the input is "b"
    Then the system should navigate to the installer dashboard

  Scenario Outline: Test notification deletion
    Given inside notification center And a customer is logged in
    And has notifications
    When the input is "<input>"
    Then the system should delete the appropriate notification
    Examples:
      | input |
      | d1    |
      | d2    |

  Scenario Outline: Test error handling
    Given inside notification center
    When the input is "<input>"
    Then the system should set an error
    Examples:
      | input |
      | 7     |
      | g     |
      | abc   |
      | -1    |