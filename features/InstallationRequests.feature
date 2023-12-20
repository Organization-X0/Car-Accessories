Feature: Appointment Confirmation

  Scenario Outline: Confirm an appointment request
    Given inside installation requests page
    When the admin chooses to confirm appointment "<AppointmentNum>"
    Then the appointment "<AppointmentNum>" should be confirmed

    Examples:
      |AppointmentNum|
      |1             |

  Scenario Outline: Confirm an appointment request with invalid number
    Given inside installation requests page
    When the admin chooses to confirm appointment "<AppointmentNum>"
    Then an error should be shown
    Examples:
      |AppointmentNum|
      |-5            |
      |abc           |


  Scenario: Confirm an appointment request with invalid option
    Given inside installation requests page
    When the admin chooses to confirm appointment "invalidOption"
    Then an error should be shown