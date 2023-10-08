#Feature: Admin Role
#  Scenario Outline: Manage "<item>"
#    Given an admin is logged in
#    When the admin "<action>" the "<item>"
#    Then the "<item>" should be "<result>" in the system
#
#    Examples:
#      | item         | action  | result   |
#      | product      | adds    | added    |
#      | product      | updates | updated  |
#      | product      | deletes | removed  |
#      | category     | adds    | added    |
#      | category     | updates | updated  |
#      | category     | deletes | removed  |
#      | userAccount  | adds    | added    |
#      | userAccount  | updates | updated  |
#      | userAccount  | deletes | removed  |

#  Scenario: Add Product
#    Given an admin is logged in
#    When the admin adds a product
#    Then the product should be added to the system
#
#  Scenario: Update Product
#    Given an admin is logged in and a product exists in the system
#    When the admin updates the product
#    Then the product should be updated in the system
#
#  Scenario: Delete Product
#    Given an admin is logged in and a product exists in the system
#    When the admin deletes the product
#    Then the product should be removed from the system
#
#  Scenario: Add Category
#    Given an admin is logged in
#    When the admin adds a category
#    Then the category should be added to the system
#
#  Scenario: Update Category
#    Given an admin is logged in and a category exists in the system
#    When the admin updates the category
#    Then the category should be updated in the system
#
#  Scenario: Delete Category
#    Given an admin is logged in and a category exists in the system
#    When the admin deletes the category
#    Then the category should be removed from the system
#
#  Scenario: Add User Account
#    Given an admin is logged in
#    When the admin adds a user account
#    Then the user account should be added to the system
#
#  Scenario: Update User Account
#    Given an admin is logged in and a user account exists in the system
#    When the admin updates the user account
#    Then the user account should be updated in the system
#
#  Scenario: Delete User Account
#    Given an admin is logged in and a user account exists in the system
#    When the admin deletes the user account
#    Then the user account should be removed from the system
