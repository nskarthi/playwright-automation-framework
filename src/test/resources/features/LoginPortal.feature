Feature: WebdriverUniversity.com - Login Page

  Background: Pre-Conditions
    Given I navigate to the webdriveruniversity homepage
    When I click on the LOGIN PORTAL link

  @regression
  Scenario Outline: Validate valid & invalid login credentials
    And I type a username "<username>"
    And I type a password "<password>"
    And I click on the login button
    Then I should be presented with an alert box with the message "<expectedAlertText>"

    Examples:
      | username  | password     | expectedAlertText    |
      | webdriver |              | validation failed    |
      |           | webdriver123 | validation failed    |
      |           |              | validation failed    |
      | webdriver | webdriver    | validation failed    |

    @smoke, @regression
    Examples:
      | username  | password     | expectedAlertText    |
      | webdriver | webdriver123 | validation succeeded |