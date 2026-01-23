@All @Register
Feature: New Customer Registering an Account

  As a new customer,
  I want to register an account,
  So that I can make  purchases as a registered customer

  Background:
    Given customer is on the accounts page

  Scenario: Verify that Customer Registers Using Valid Credentials
    When the customer registers with the credentials:
      | username   | email                | password          |
      | John Jones | john.jones@gmail.com | john.password@123 |
    Then account details page shows username "John Jones"

  Scenario: Verify that Customer registers with invalid credentials
    When the customer registers with the credentials:
      | username | email                | password |
      | *******  | joney.fast@gmail.com | fast.123 |
    Then the account page provides error message: "Error: Please enter a valid account username."

  Scenario: Verify that New Customer Registers with Credentials of an Existing Customer
    When the customer registers with the credentials:
      | username   | email                | password          |
      | John Jones | john.jones@gmail.com | john.password@123 |
    Then the account page provides error message: "Error: An account is already registered with your email address. Please log in."





