Feature: Checkout page functionality

  Background:
    Given I have a product in cart
    And I am on the checkout page


  @firstone
  Rule: Complete checkout with Billing and Payment details

  Scenario: Verify that checkout has successfully done with valid billing and payment details
    When I provide billing details
      | firstName | lastName | country | address   | city   | state  | zipCode | email                   |
      | ISHIMWE   | Julien   | Rwanda  | KG 555 St | Kigali | Gasabo | 10001   | chrismbonimpa@gmail.com |
    And I place the order
    Then the order should be placed successfully

  Scenario: Verify validation when billing and shipping forms have missing required fields
    When I provide billing details
      | firstName | lastName | country            | address     | city  | state | zipCode | email             |
      |           | Julien   | United States (US) | 123 Main St | Holly | NY    | 10001   | jul.ish@gmail.com |
    And I place the order
    Then I should see error message for required field: "Billing First name is a required field."

  @secondone
  Rule: Place order with separate billing and shipping addresses

  Scenario: Verify checkout with billing details only
    When I provide billing details
      | firstName | lastName | country | address   | city   | state  | zipCode | email             |
      | ISHIMWE   | Julien   | Rwanda  | KG 555 St | Kigali | Gasabo | 10001   | john.doe@test.com |
    And I place the order
    Then the order should be placed successfully


  Scenario: Verify checkout with billing details and different shipping address
    When I provide billing details
      | firstName | lastName | country | address   | city   | state  | zipCode | email                   |
      | ISHIMWE   | Julien   | Rwanda  | KG 555 St | Kigali | Gasabo | 10001   | chrismbobimpa@gmail.com |
    And I provide different shipping details
      | firstName | lastName | country            | address        | city   | state |
      | Jane      | Smith    | United States (US) | 45 Market Road | Austin | TX    |
    And I place the order
    Then the order should be placed successfully
