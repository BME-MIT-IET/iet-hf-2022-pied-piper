Feature: Are they isomorphic?
  It is important to know if the same graph is written twice. They are still isomorphic.

  Scenario: They are isomorphic
    Given there is a set of data
    When I ask whether two graphs from the same data are isomorphic
    Then I should be told they are isomorphic

  Scenario: They are not isomorphic
    Given there are two sets of data
    When I ask whether two graphs from different data are isomorphic
    Then I should be told they are not isomorphic