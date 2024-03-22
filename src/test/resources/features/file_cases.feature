Feature: Valid File Senarios

  @Sanity
  @Smoke
  Scenario: Give valid input file content
    Given the following file name "src/test/resources/valid_file.txt"
    When I parse the file
    Then I got a result is multiple of 2 with header

  @Sanity
  @Smoke
  Scenario: Give an empty input file content
    Given the following file name "src/test/resources/empty_file.txt"
    When I parse the file
    Then I got GroundFileInvalidException

  @Sanity
  @Smoke
  Scenario: Give an invalid input file content
    Given the following file name "src/test/resources/invalid_file.txt"
    When I parse the file
    Then I got GroundFileInvalidException with result not multiple of 2 with header

  @Sanity
  @Smoke
  Scenario: Give an file not found
    Given the following file name "src/test/resources/not_found.txt"
    When I parse the file
    Then I got GroundFileNotFoundException

  @Smoke
  Scenario: Extra scenario to not be executed
    Given the following file name "src/test/resources/not_found.txt"
    When I parse the file
    Then I got any exception