Feature: Appointment Confirmation

  Scenario Outline: Confirm an appointment request
    Given inside installation requests page
    When the admin chooses to confirm appointment "<AppointmentNum>"
    Then the appointment "<AppointmentNum>" should be confirmed

    Examples:
      |AppointmentNum|
      |1             |