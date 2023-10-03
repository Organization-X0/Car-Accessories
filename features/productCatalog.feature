Feature: Product Catalog
  Scenario: Add a new product
    Given an admin is logged in
    When the admin adds a new product with valid details
    Then the product should be added to the catalog

  Scenario: Update an existing product
    Given an admin is logged in
    When the admin updates the product with new details
    Then the product details should be updated in the catalog

  Scenario: Delete a product
    Given an admin is logged in
    When the admin deletes the product
    Then the product should be removed from the catalog

  Scenario: Browse products as a customer
    Given a customer is logged in
    When the customer browses the product catalog
    Then they should see a list of available products

  Scenario: Search for a product as a customer
    Given a customer is logged in
    When the customer searches for a product by name
    Then they should see a list of matching products in the catalog