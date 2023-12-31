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

  Scenario Outline: Add a new category
    Given an admin is logged in
    And admin enters manage categories page
    When the admin adds new category "<category>"
    Then a new category "<category>" should be created
    Examples:
      | category |
      | Motors   |
      | Decorates|
      | Radio    |

  Scenario Outline: Add a category with invalid input
    Given an admin is logged in
    And admin enters manage categories page
    When the admin adds new category "<category>"
    Then an error should be displayed
    Examples:
      | category |
      |          |
      | 123456   |

  Scenario: Add a category that already exists
    Given an admin is logged in
    And admin enters manage categories page
    When the admin adds new category "Interior"
    Then an error should be displayed


  Scenario Outline: Update an existing category
    Given an admin is logged in
    And admin enters manage categories page
    When the admin updates the category "<oldCategoryNum>" to "<newCategory>"
    Then the category should be updated "<newCategory>"
    Examples:
      | oldCategoryNum | newCategory |
      | 1              | Category x  |
      | 2              | Category y  |

  Scenario Outline: Delete a category
    Given an admin is logged in
    And admin enters manage categories page
    When the admin deletes the category "<categoryNum>"
    Then the category "<categoryNum>" should be deleted

    Examples:
      | categoryNum  |
      | 3            |
      | 2            |

  Scenario Outline: Delete a non-existing category
    Given an admin is logged in
    And admin enters manage categories page
    When the admin deletes the category "<categoryNum>"
    Then an error should be displayed
    Examples:
      | categoryNum  |
      | 100          |
      | 200          |

  Scenario Outline: Update a non-existing category
    Given an admin is logged in
    And admin enters manage categories page
    When the admin updates the category "<oldCategoryNum>" to "<newCategory>"
    Then an error should be displayed
    Examples:
      | oldCategoryNum | newCategory |
      | 100            | Category z  |
      | 200            | Category w  |


  Scenario Outline: Add a new product
    Given an admin is logged in
    And admin enters manage product page
    When the admin adds new product "<Category>","<Product Name>","<Description>","<Price>"
    Then a new product listing should be created
    Examples:
      | Category | Product Name | Description | Price |
      | 1 | itemX1 | Hard material | 699 |
      | 2 | itemX22 | soft material | 1.33 |

  Scenario Outline: Update an existing product
    Given an admin is logged in
    And admin enters manage product page
    When the admin updates the product "<ProductID>" to "<New Category>","<New Product Name>","<New Description>","<New Price>"
    Then the product should be updated "<ProductID>", "<New Product Name>"
    Examples:
      | ProductID    | New Category | New Product Name | New Description | New Price |
      | 1            | 2            | itemX2           | Soft material   | 599       |
      | 2            | 3            | itemX33          | Medium material | 2.33      |

  Scenario Outline: Delete an existing product
    Given an admin is logged in
    And admin enters manage product page
    When the admin deletes the product "<ProductID>"
    Then the product "<ProductID>" should be deleted
    Examples:
      |ProductID|
      |1        |
      |2        |

  Scenario Outline: Update the name or the phone to account
    Given an admin is logged in
    And admin enters manage accounts page
    When the admin updates the account "<AccountNum>" to "<New Name>","<New Phone>"
    Then the account should be updated "<New Name>","<New Phone>"
    Examples:
      | AccountNum| New Name | New Phone  |
      | 1         | Jane Doe | 0987654321 |
      | 2         | Bob      | 0333222111 |

  Scenario Outline: Delete user account
    Given an admin is logged in
    And admin enters manage accounts page "3"
    When the admin delete account "<AccountNum>"
    Then the account should be deleted
    Examples:
      |AccountNum|
      |1         |
      |2         |

  Scenario: View customer accounts
    Given an admin is logged in
    When admin enters manage accounts page "3"
    Then the user accounts should show

  Scenario Outline: Schedule installation appointments
    Given an admin is logged in
    And admin enters manage installation page "4"
    When add new installation appointments "<UserEmail>", "<ProductName>","<CarMake>","<Date>","<Time>"
    Then the appointments should be added
    Examples:
      |UserEmail      |ProductName|CarMake|Date     |Time|
      |user1@gmail.com|item1      |bmw    |2023-11-8|3   |
      |user2@gmail.com|item2      |toyota |2023-11-9|1   |

  Scenario: Schedule installation appointments Empty Data
    Given an admin is logged in
    And admin enters manage installation page "4"
    When the user adds an empty appointment
    Then the application should return without adding an appointment

  Scenario Outline: Schedule installation appointments Invalid Date or Non-existent Account
    Given an admin is logged in
    And admin enters manage installation page "4"
    When add new installation appointments "<UserEmail>", "<ProductName>","<CarMake>","<Date>","<Time>"
    Then the application should throw an Error

    Examples:
      |UserEmail      |ProductName|CarMake|Date     |Time|
      | user1@gmail.com   | product1    | car1    | invalid    | 1    |
      | nonexistent@example.com| product2    | car2    | 2023-12-19 | 2    |

  Scenario Outline: Delete installation appointments
    Given an admin is logged in
    And admin enters manage installation page "4"
    When the admin deletes appointment "<AppointmentNum>"
    Then the appointment should be deleted "<AppointmentNum>"
    Examples:
      |AppointmentNum|
      |1             |

  Scenario Outline: Update an appointment
    Given an admin is logged in
    And admin enters manage installation page "4"
    And admin enters "<AppointmentNum>" to update
    When admin update appointment "<AppointmentNum>" to "<UserEmail>", "<ProductName>","<CarMake>","<Date>"
    Then the appointment should be updated "<AppointmentNum>" , "<UserEmail>", "<ProductName>","<CarMake>","<Date>"
    Examples:
      |AppointmentNum|UserEmail      |ProductName|CarMake|Date     |
      |1             |user1@gmail.com|item1      |bmw    |2023-11-8|


  Scenario: Invalid option
    Given an admin is logged in
    And admin enters manage installation page "4"
    When the admin enters an invalid option "invalidOption"
    Then an error should be shown

  Scenario: Test nextPage Manage Appointment
    Given inside manage appointment
    And the current page is not the total pages
    When the input is "n"
    Then the system should navigate to the next page

  Scenario: Test previous page Manage Appointment
    Given inside manage appointment
    And the current page is not the first page
    When the input is "p"
    Then the system should navigate to the previous page

  Scenario: Test back from Manage Appointment
    Given inside manage appointment
    When the input is "b"
    Then the system should navigate to admin dashboard

  Scenario Outline: Test notification deletion
    Given inside manage appointment
    And has appointments
    When the input is "<input>"
    Then the system should delete the appropriate appointment
    Examples:
      | input |
      | d1    |

  Scenario Outline: Test error handling
    Given inside manage appointment
    When the input is "<input>"
    Then the system should set an error
    Examples:
      | input |
      | 7     |
      | g     |
      | abc   |
      | -1    |