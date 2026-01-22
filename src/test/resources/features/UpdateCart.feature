Feature: Update product quantity in cart

  In order to manage my shopping cart before checkout
  As a customer
  I want to update the quantity of a product in the cart successfully

  Scenario: Update product quantity in the cart
    Given the customer has a product in the cart
    And the customer is on the cart page
    When the customer updates the product quantity
    Then the cart should be updated successfully
