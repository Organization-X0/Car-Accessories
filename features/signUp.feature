#Feature: User Sign Up
#
#  Scenario: Successful Sign Up
#    Given I am on the sign up page
#    When I enter valid details
#    And  Press enter key
#    Then Go to main page
#
#  Scenario: Unsuccessful Sign Up
#    Given I am on the sign up page
#    When entering invalid details or missing information
#    And  Press enter key
#    Then I should see an error message indicating what went wrong

Feature: User Sign Up

  Scenario: Successful Sign Up
    Given on the sign up page
    When valid details are entered
    And the enter key is pressed
    Then go to the main page

  Scenario: Unsuccessful Sign Up
    Given on the sign up page
    When invalid details or missing information are entered
    And the enter key is pressed
    Then Show error message indicates what went wrong