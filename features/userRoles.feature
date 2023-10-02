Feature: User Roles

  Scenario: Admin Role
    Given an admin is logged in
    When the admin accesses the admin dashboard
    Then the admin should be able to manage products
    And manage categories
    And manage user accounts

  Scenario: Customer Role
    Given a customer is logged in
    When the customer accesses the product catalog
    Then the customer should be able to browse products
    And make purchases
    And view orders

  Scenario: Installer Role
    Given an installer is logged in
    When the installer accesses their dashboard
    Then the installer should be able to view installation requests
    And schedule appointments
