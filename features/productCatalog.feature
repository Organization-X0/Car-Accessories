Feature: Product Catalog

  Scenario: View Categories
    Given the user is logged in
    When the user enter product catalog
    Then the user should see categories

  Scenario: View Product Listings
    Given the user is logged in
    When the user enter product catalog
    And the user enter All product or specific category
    Then each listing should show product descriptions, prices, and availability

  Scenario: Search and Filter Products
    Given the user is logged in
    When the user enter product catalog
    And the user enter search page
    And  searches for a product with product name
    Then the user should see filtered product listings

  Scenario: Handle View Products
    Given inside view products
    And the current page is not the total pages
    When the input is "n"
    Then the system should navigate to the next page

  Scenario: Handle View Products
    Given inside view products
    And the current page is not the first page
    When the input is "p"
    Then the system should navigate to the previous page

  Scenario: Handle View Products
    Given inside view products
    When the input is "b"
    Then the system should navigate to product Catalog