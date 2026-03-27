Feature: WebdriverUniversity.com - Contact Us Page

  Background: Pre-Conditions
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button

  @regression, @smoke, @contact-us-optimized
  Scenario: Valid Contact Us form submission via optimized feature file
    And I type "Joe" into the "first name" field
    And I type "Bloggs" into the "last name" field
    And I type "joe@test.com" into the "email" field
    And I type "Testing 123" into the "comment" field
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @smoke, @contact-us-optimized, @fix
  Scenario: Valid Contact Us form submission via optimized feature file - Using Random Data
    And I type a random first name
    And I type a random last name
    And I type a random email
    And I type a random comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @regression, @contact-us-optimized
  Scenario Outline: Invalid Contact Us form submission via optimized feature file
    And I enter "<firstName>" as "first name"
    And I enter "<lastName>" as "last name"
    And I enter "<email>" as "email"
    And I enter "<comment>" as "comment"
    And I click on the submit button
    Then I should see the error "<error>"

    Examples:

      | firstName | lastName | email        | comment | error                          |
      |           | Bloggs   | joe@test.com | Hello   | Error: all fields are required |
      | Joe       |          | joe@test.com | Hello   | Error: all fields are required |
      | Joe       | Bloggs   |              | Hello   | Error: all fields are required |
      | Joe       | Bloggs   | invalid-mail | Hello   | Error: Invalid email address   |
      | Joe       | Bloggs   | joe@test.com |         | Error: all fields are required |
