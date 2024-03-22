Feature: Valid Senarios

  @Sanity
  @Smoke
  Scenario: Give valid input file
    Given the following lines:
      | 5 5                     |
      | 1 2 N                   |
      | GAGAGAGAA               |
      | 3 3 E                   |
      | AADAADADDA              |
      | 1 3 N                   |
      | GAGAGAGAAGAGAAAAAAAAA   |
      | 1 3 N                   |
      | GAGAGAGAAGAGAAAAAAAAADD |
      | 1 2 N                   |
      | GAGAGAGAAGAGDDAAAAAAAAA |
      | 3 3 E                   |
      | AAAAAAAAAAA    |
      | 3 3 W                   |
      | AAAAAAAAAAA    |

    When I process lines
    Then I got the result
      | 1 3 N |
      | 5 1 E |
      | 0 0 S |
      | 0 0 N |
      | 0 5 N |
      | 5 3 E |
      | 0 3 W |