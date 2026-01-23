@All @AddToCart
Feature: Add products to cart
  As a customer
  I want to add products to the cart successfully
  In order to purchase a product

Background:
    Given I am on the Store Page
  Scenario: Add multiple products to cart
    When I add the following products to the cart:
      | Product Name                    |
      | Black Over-the-shoulder Handbag |
      | Anchor Bracelet                 |
    Then the cart should contain these items

Scenario: Update product quantity in cart
    And I have "Black Over-the-shoulder Handbag" in the cart
    When I update the quantity of "Black Over-the-shoulder Handbag" to 3
    Then the cart should reflect the updated quantity
