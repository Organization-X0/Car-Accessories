Feature: User Login

  Scenario: Successful Login
    Given on the login page
    When valid username and password are entered
    And the enter key is pressed
    Then redirection to the main page should occur

  Scenario: Unsuccessful Login
    Given on the login page
    When invalid username or password are entered
    And the enter key is pressed
    Then an error message should be seen
