Feature: Label reading is working correctly?
  We want to know if labels on elements work correctly

  Scenario: Label is the same random value
    Given there is a company with random number of employees
    When I ask whether it has the label 'numberOfEmployees'
    Then I should be told they have that many employees

  Scenario: Label is the same we set
    Given there is a company with 10 number of employees
    When I ask whether it has the label 'numberOfEmployees' again
    Then I should be told they have 10 employees