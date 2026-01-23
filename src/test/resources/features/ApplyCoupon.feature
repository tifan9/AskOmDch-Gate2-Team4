Feature: Add product to cart and apply coupon

  In order to purchase a product with a discount
  As a customer
  I want to add a specific product to the cart and apply a coupon successfully

  Background:
    Given the customer has product in the cart
    And the customer is on the cart page

  Scenario: Add a specific product to cart and apply coupon
  When the customer applies the coupon "off25"
  Then the coupon should be applied successfully

  Scenario: Add a specific product to cart and apply wrong coupon
    When the customer applies the coupon "off30"
    Then the coupon should not be applied

