Feature: Admin Dashboard

  Scenario: Admin Dashboard page to Manage Products
    Given an admin is logged in
    When the admin enter "1"
    Then should be redirected to the Manage Products

  Scenario: Admin Dashboard page to Manage Categories
    Given an admin is logged in
    When the admin enter "2"
    Then should be redirected to the Manage Categories

  Scenario: Admin Dashboard page to Manage User Accounts
    Given an admin is logged in
    When the admin enter "3"
    Then should be redirected to the Manage User Accounts

  Scenario: Admin Dashboard page to Manage Installation Appointments
    Given an admin is logged in
    When the admin enter "4"
    Then should be redirected to the Manage Installation Appointments

  Scenario: Admin Dashboard page to Log out
    Given an admin is logged in
    When the admin enter "5"
    Then should be redirected to the Log out

  Scenario Outline: Invalid input admin dashboard
    Given an admin is logged in
    When the admin enter "<input>"
    Then  should see an error message on Admin Dashboard
    Examples:
      | input |
      | 7     |
      | g     |
      | abc   |
      | -1    |





  ##TODO: Add more Scenarios.

  Scenario: Add a new category
    Given an admin is logged in
    When the admin adds new category
    Then a new category should be created

  Scenario: Update an existing category
    Given an admin is logged in
    When the admin updates the category
    Then the category should be updated

  Scenario: Delete a category
    Given an admin is logged in
    When the admin delete category
    Then the category should be deleted

  Scenario: Add a new product
    Given an admin is logged in
    When the admin adds new product
    Then a new product listing should be created

  Scenario: Update an existing product
    Given an admin is logged in
    When the admin updates the product
    Then the product should be updated

  Scenario: Delete an existing product
    Given an admin is logged in
    When the admin delete product
    Then the product should be deleted

  Scenario: Delete an existing account
    Given an admin is logged in
    When the admin delete account
    Then the account should be deleted

  Scenario: Update the name or the phone to account
    Given an admin is logged in
    When the admin update account
    Then the account should be updated

  Scenario: View customer accounts
    Given an admin is logged in
    When the admin enter manage user accounts
    Then the user accounts should show

  Scenario: Schedule installation appointments
    Given an admin is logged in
    When add new installation appointments
    Then the appointments should be added

  Scenario: Delete installation appointments
    Given an admin is logged in
    When the admin deletes appointment
    Then the appointment should be deleted

  Scenario: Update date in appointment
    Given an admin is logged in
    When admin update appointment
    Then the appointment should be updated