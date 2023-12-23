Feature: User Sign Up

  Scenario: Successful Sign Up
    Given on the sign up page
    When valid details are entered
    Then go to the login page

  Scenario: Unsuccessful Sign Up
    Given on the sign up page
    When invalid details or missing information are entered
    Then Show error message indicates what went wrong

  Scenario: Invalid input type
    Given on the sign up page
    When input of invalid type is entered
    Then an error message should be seen
