Feature: User Sign Up

  Scenario: Successful Sign Up
    Given on the sign up page
    When valid details are entered
    Then go to the main page

  Scenario: Unsuccessful Sign Up
    Given on the sign up page
    When invalid details or missing information are entered
    Then Show error message indicates what went wrong