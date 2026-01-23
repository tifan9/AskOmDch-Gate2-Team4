Feature: Sort Product
  As a Customer,
  I want to sort products by different criteria
  So that I can browse products in my preferred order
    Background:
        Given I am on the product listing page
      

    Scenario Outline: Sort products by different criteria
        When I select "<sortOption>" from the sort dropdown
        Then products should be sorted by "<sortOption>"
        
        Examples:
        | sortOption           |
        | Sort by price: low to high  |
        | Sort by price: high to low  |
#        | Popularity           |
#        | Average Rating       |
#        | Latest               |