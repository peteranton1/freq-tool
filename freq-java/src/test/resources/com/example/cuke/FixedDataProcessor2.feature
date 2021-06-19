Feature: Ensure FixedDataProcessor can work

  Background:
    Given I have the following data in the reader
      | file                                     |
      | ../data/lion-witch-data.txt              |


  Scenario: Fields 1: FDP can produce expected result
    Given my columns are "4" to "12"
    When A Fixed Data Processor is created and run
    Then I expect the following results
      | field        | count |
      | \|The Devi\| |     3 |
      | \|The Lion\| |     2 |
      | \|In the G\| |     1 |

  Scenario: Fields 2: FDP can produce expected result
    Given my columns are "4" to "12"
    And my columns are "14" to "17"
    When A Fixed Data Processor is created and run
    Then I expect the following results
      | field             | count |
      | \|The Devi\|in \| |     3 |
      | \|The Lion\|the\| |     2 |
      | \|In the G\|den\| |     1 |

  Scenario: Fields 3: FDP can produce expected result
    Given my columns are "4" to "12"
    And my columns are "14" to "17"
    And my columns are "31" to "33"
    When A Fixed Data Processor is created and run
    Then I expect the following results
      | field                 | count |
      | \|The Devi\|in \|\|   |     3 |
      | \|The Lion\|the\| W\| |     2 |
      | \|In the G\|den\|\|   |     1 |
