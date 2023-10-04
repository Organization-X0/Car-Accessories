Feature: Admin Dashboard

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
    When the admin deletes appiontement
    Then the appointment should be deleted

  Scenario: Update date in appointment
    Given an admin is logged in
    When admin update appointment
    Then the appointment should be updated