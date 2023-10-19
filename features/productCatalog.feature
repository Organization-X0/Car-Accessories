Feature: Product Catalog

  Scenario: View Categories
    Given the user is logged in and on product catalog page
    When the user views the categories
    Then the user should see categories like "Interior", "Exterior", and "Electronics"

  Scenario: View Product Listings
    Given the user is logged in and on a category page
    When the user views the product listings
    Then each listing should show product descriptions, prices, and availability

  Scenario: Search and Filter Products
    Given the user is logged in and on the product catalog page
    When the user searches for a product with product name
    Then the user should see filtered product listings