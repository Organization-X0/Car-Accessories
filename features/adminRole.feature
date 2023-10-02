Feature: Admin Role

  Scenario: Manage Products
    Given an admin is logged in
    When the admin manages products
    Then the changes should be reflected in the system

  Scenario: Manage Categories
    Given an admin is logged in
    When the admin manages categories
    Then the changes should be reflected in the system

  Scenario: Manage User Accounts
    Given an admin is logged in
    When the admin manages user accounts
    Then the changes should be reflected in the system