Feature: Navigation

  Scenario: Go to login page
    Given I am on the start page
    When I enter 1
    Then I should be redirected to the login page

  Scenario: Go to signup page
    Given I am on the start page
    When I enter 2
    Then I should be redirected to the signup page

  Scenario Outline: Invalid input
    Given I am on the start page
    When I enter <input>
    Then I should see an error message
    Examples:
      | input |
      | 3     |
      | 4     |
      | a     |
      | abc   |
      | -1    |
