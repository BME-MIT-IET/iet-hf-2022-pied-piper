Feature: Can we map unidentifiable objects?
  We want to know if maping unidentifiable objects throws an exception.

  Scenario: An exception was thrown
    Given there is an empty map created
    When I ask whether it is writable without any exceptions
    Then I should be told they are not

  Scenario: An exception was not thrown
    Given there is a map created with one element
    When I ask whether it is written without any exceptions
    Then I should be told it is