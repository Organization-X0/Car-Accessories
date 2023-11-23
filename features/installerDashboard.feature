Feature: Installation Dashboard

  Scenario: Installation Dashboard page to Schedule of Appointments
    Given an installer is logged in
    When the installer enter "1"
    Then should be redirected to the Schedule of Appointments page

  Scenario: Installation Dashboard page to Installation requests
    Given an installer is logged in
    When the installer enter "2"
    Then should be redirected to the Installation requests page

  Scenario: Installation Dashboard page to Notification Center
    Given an installer is logged in
    When the installer enter "3"
    Then should be redirected to the Notification Center page

  Scenario: Installation Dashboard page to Log out
    Given an installer is logged in
    When the installer enter "4"
    Then should be redirected to the Log out page

  Scenario Outline: Invalid input Installation dashboard
    Given an installer is logged in
    When the installer enter "<input>"
    Then  should see an error message on installer Dashboard
    Examples:
      | input |
      | 7     |
      | g     |
      | abc   |
      | -1    |
  Scenario: Confirm a Appointment
    Given an installer is logged in
    And installer enters Schedule of Appointments page
    When the installer confirm the Appointment
    Then the appointment should be deleted from Schedule  Appointments
