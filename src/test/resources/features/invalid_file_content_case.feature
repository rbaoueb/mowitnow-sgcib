Feature: Valid Senarios

  @Sanity
  @Smoke
  Scenario: Give Invalid Header File
    Given the following lines:
      | 5 5A |
    When I process lines
    Then I got HeaderFileInvalidException

  @Sanity
  @Smoke
  Scenario: Give Blank Header File
    Given the following lines:
      |  |
    When I process lines
    Then I got HeaderFileInvalidException

  @Sanity
  @Smoke
  Scenario: Give Invalid Mower Information
    Given the following lines:
      | 5 5       |
      | 1 2E N    |
      | GAGAGAGAA |
    When I process lines
    Then I got PositionInvalidException

  @Sanity
  @Smoke
  Scenario: Give Blank Mower Information
    Given the following lines:
      | 5 5       |
      |           |
      | GAGAGAGAA |
    When I process lines
    Then I got PositionInvalidException

  @Sanity
  @Smoke
  Scenario: Give Invalid Control Information
    Given the following lines:
      | 5 5          |
      | 1 2 N        |
      | GAGASSSGAGAA |
    When I process lines
    Then I got CommandInvalidException

  @Sanity
  @Smoke
  Scenario: Give Blank Control Information
    Given the following lines:
      | 5 5   |
      | 1 2 N |
      |       |
    When I process lines
    Then I got CommandInvalidException