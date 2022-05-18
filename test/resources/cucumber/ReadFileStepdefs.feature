Feature: File reading is working correctly?
  We want to know if reading from a file gets the same result as creating it from code

  Scenario: We read correctly
    Given there is a file with data
    When I ask whether it is the same as the one we created
    Then I should be told they are the same
  Scenario: We read incorrectly
    Given there is another file with data
    When I ask whether it is still the same as the one we created
    Then I should be told they are not the same