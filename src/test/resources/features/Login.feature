Feature: Customer logging in
  As a registered customer,
  I want to log into my account,
  So that I can access my account and make purchases

  Background:
    Given customer is on the accounts page

  Scenario: Existing customer logs in with valid credentials
    When the customer logs in with valid credentials
      | username | boomer_ang      |
      | password | boomer.password |
    Then account details page shows username "boomer_ang"

