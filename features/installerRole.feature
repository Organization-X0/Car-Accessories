Feature: Installer Role

  Scenario: View Installation Requests
    Given an installer is logged in
    When the installer views installation requests
    Then the requests should be displayed

  Scenario: Schedule Appointments
    Given an installer is logged in and there are pending installation requests
    When the installer schedules appointments
    Then the appointments should be updated in the system
