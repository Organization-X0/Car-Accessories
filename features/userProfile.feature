Feature: User Profile
  Scenario: profile page to Edit profile
    Given an customer is logged in and in profile page
    When the customer enter "1"
    Then should be redirected to the edit profile

  Scenario: profile page to view order history
    Given an customer is logged in and in profile page
    When the customer enter "2"
    Then should be redirected to the View order history

  Scenario: profile page to view installation history
    Given an customer is logged in and in profile page
    When the customer enter "3"
    Then should be redirected to the view installation history

  Scenario: profile page to back to customer dashboard
    Given an customer is logged in and in profile page
    When the customer enter "4"
    Then should be redirected to the back to customer dashboard

 Scenario: Customer can edit their profile
    Given an customer is logged in and in profile page
    When the customer edit their profile
    Then the profile should be edited

 Scenario: Customers can view order history
    Given an customer is logged in and in profile page
    When the customer navigates to their order history
   Then the customer should be able to view all past orders

#  Scenario: Customers can view installation requests
#    Given a customer is logged into their profile
#    When the customer navigates to their installation requests
#   Then the customer should be able to view all installation requests
