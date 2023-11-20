Feature: User Roles

  Scenario: Admin role
    Given an admin is logged in
    Then the admin should be able to manage products, categories, and user accounts

  Scenario: Customer role
    Given a customer is logged in
    Then the customer should be able to browse products, make purchases, and view orders.

  Scenario: Installer role
    Given an installer is logged in
    Then the installer should be able to view installation requests and schedule appointments.