@Regression @QA1
Feature: QA1 Ensure FixedDataProcessor works with immediate data

  Background:
    Given I have the following data in the reader
      | line                                     |
      | ABC The Devil in the White City          |
      | HIG In the Garden of Beasts              |
      | DEF The Lion, the Witch and the Wardrobe |
      | ABC The Devil in the White City          |
      | ABC The Devil in the White City          |
      | DEF The Lion, the Witch and the Wardrobe |


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
