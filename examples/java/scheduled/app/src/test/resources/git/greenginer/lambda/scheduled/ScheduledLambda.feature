Feature: Scheduled lambda
  #The general idea with these tests is they are evaluating what the lambda did based on the
  #response returned by the lambda.

  Scenario: detects retry events and updates status via EDS
    Given a scheduled lambda
    When the lambda is invoked
    Then the lambda result begins with Hello
