Feature: Apply coupon

  In order to purchase a product with a discount
  As a customer
  I want to add a specific product to the cart and apply a coupon successfully

  Background:
    Given the customer has product in the cart
    And the customer is on the cart page

  Scenario: Customer applies coupon
  When the customer applies the coupon "off25"
    Then the coupon successfully message should be displayed


  Scenario: Customer applies wrong coupon
    When the customer applies the coupon "off30"
    Then an error message should be displayed


