Feature: Installation Requests
  Scenario: Customer request installation service
    Given customer is logged in and requesting installation services
    When customer fill in the installation request form
    Then customer installation request should be submitted

#  Scenario: Customer can see when the installer is available
#    Given customer is logged in and requesting installation services
#    When customer submitted installation request
#    Then customer should see when the installer is available for service