Feature: Add product to cart
  As a customer
  I want to add a specific product to the cart successfully
  In order to purchase a product

Background:
    Given I am on the Store Page
  Scenario: Add a specific product to cart
    When I add "Black Over-the-shoulder Handbag" to the cart
    Then the product should be added to the cart successfully
    And I should see the product in the cart

  Scenario: Add multiple products to cart and verify totals
    When I add the following products to the cart:
      | Product Name                    |
      | Black Over-the-shoulder Handbag |
      | Anchor Bracelet                 |
    Then the cart should contain these items
    And the cart total should reflect the sum of all item prices

Scenario: Update product quantity in cart
    And I have "Black Over-the-shoulder Handbag" in the cart
    When I update the quantity of "Black Over-the-shoulder Handbag" to 3
    Then the cart should reflect the updated quantity
    And the cart total should be updated accordingly