Feature: Product Catalog

  Scenario: View Categories
    Given the user is logged in
    When the user enter product catalog
    Then the user should see categories

  Scenario: View Product Listings
    Given the user is logged in
    When the user enter All product or specific category
    Then each listing should show product descriptions, prices, and availability

  Scenario: Search and Filter Products
    Given the user is logged in
    When the user enter searche page
    And  searches for a product with product name
    Then the user should see filtered product listings