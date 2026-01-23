@All @ProductView
Feature: View Product Details

  Scenario: View product details successfully
    Given I am on the Store Page
    When I select the "Anchor Bracelet"
    Then I should see the full product information for "Anchor Bracelet"
