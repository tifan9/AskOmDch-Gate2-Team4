@All @SearchProduct
Feature: Search Product
  As a Customer I want to search for products using keywords
  So that I can quickly find specific products I'm looking for
  Background:
    Given I am on the store page

  Scenario Outline: Search for an existing product
    When I search for "<keyword>"
    Then I should see a list of products related to "<keyword>"
    Examples:
      | keyword        |
      | Shirt          |
      | Jeans          |
      | Shoes          |

  Scenario: Search for a non-existing product
    When I search for "NonExistingProduct123"
    Then I should see a message indicating no products were found