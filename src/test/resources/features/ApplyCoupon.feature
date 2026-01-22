Feature: Add product to cart and apply coupon

  In order to purchase a product with a discount
  As a customer
  I want to add a specific product to the cart and apply a coupon successfully

  Scenario: Add a specific product to cart and apply coupon

    Given the customer has product in the cart
    And the customer is on the cart page
    When the customer applies the coupon "off25"
    Then the coupon should be applied successfully