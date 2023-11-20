Feature: Installation Requests
  Scenario: Customer request installation service
    Given customer is logged in and requesting installation services
    When customer fill in the installation request form
    Then customer should see when the installer is available for service
    And customer installation request should be submitted