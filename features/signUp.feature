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

  Scenario: Sign Up with valid email but short full name
    Given on the sign up page
    When full name is less than three characters and other details are valid
    Then Show error message indicates what went wrong

  Scenario: Sign Up with valid email but long full name
    Given on the sign up page
    When full name is more than fifty characters and other details are valid
    Then Show error message indicates what went wrong

  Scenario: Sign Up with valid email but short password
    Given on the sign up page
    When password is less than three characters and other details are valid
    Then Show error message indicates what went wrong

  Scenario: Sign Up with valid email but long password
    Given on the sign up page
    When password is more than twenty characters and other details are valid
    Then Show error message indicates what went wrong

  Scenario: Sign Up with valid email but invalid phone number
    Given on the sign up page
    When phone number is not ten digits and other details are valid
    Then Show error message indicates what went wrong

