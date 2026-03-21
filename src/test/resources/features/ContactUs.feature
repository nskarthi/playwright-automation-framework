Feature: WebdriverUniversity.com - Contact Us Page

  Scenario: Valid Contact Us form submission
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    And I type a last name
    And I type an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  Scenario: Invalid Contact Us form submission without email address
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    And I type a last name
    #And I type an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with an error page for missing email address
      | Error: all fields are required |
      | Error: Invalid email address   |

  Scenario: Invalid Contact Us form submission without first name
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button
    #And I type a first name
    And I type a last name
    And I type an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with an error page for missing "first name"
      | Error: all fields are required |

  Scenario: Invalid Contact Us form submission without last name
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    #And I type a last name
    And I type an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with an error page for missing "last name"
      | Error: all fields are required |

  Scenario: Invalid Contact Us form submission without comments
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    And I type a last name
    And I type an email address
    #And I type a comment
    And I click on the submit button
    Then I should be presented with an error page for missing "comments"
      | Error: all fields are required |