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
      | firstName | lastName | country | address   | city   | state  | zipCode | email             |
      |           | Julien   | Rwanda  | KK 423 st | Kigali | Kigali | 00000   | jul.ish@gmail.com |
    And I place the order
    Then I should see error message for required field: "Billing First name is a required field."

    @secondone
  Rule: Place order with separate billing and shipping addresses

    Scenario: Verify checkout with billing details and different valid shipping address
      When I provide billing details
        | firstName | lastName | country | address   | city   | state  | zipCode | email                   |
        | ISHIMWE   | Julien   | Rwanda  | KG 555 St | Kigali | Gasabo | 10001   | chrismbobimpa@gmail.com |
      And I provide different shipping details
        | firstName | lastName | country | address  | city    | state  | zipCode |
        | Jane      | Smith    | Rwanda  | ibereshi | Musanze | Rwanda | 00000   |
      And I place the order
      Then the order should be placed successfully

    Scenario: Verify checkout with billing details and different invalid shipping address
      When I provide billing details
        | firstName | lastName | country | address   | city   | state  | zipCode | email                   |
        | ISHIMWE   | Julien   | Rwanda  | KG 555 St | Kigali | Gasabo | 00000   | chrismbobimpa@gmail.com |
      And I provide different shipping details
        | firstName | lastName | country | address | city | state  | zipCode |
        | Jane      | Smith    | Rwanda  |         | KG   | Kigali | 00000   |
      And I place the order
      Then I should see error message for required field: "Shipping Street address is a required field."