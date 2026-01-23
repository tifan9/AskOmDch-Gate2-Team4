Feature: Filter Product
    As a customer,
    I want to filter products by selecting a price range,
    So that I can quickly find items within my budget.
Background:
    Given I am on the product listing page

    Scenario: Successfully filter products within a price range
        When I filter products with minimum price 20 and maximum price 50
        Then I should see only products priced between 20 and 50

    Scenario Outline: Filter products by Category
        When I select the category "<CategoryName>"
        Then I should see only products in that "<CategoryName>"
        
        Examples:
        |CategoryName|
        |Men  (7)   |
       |Women  (7)  |

