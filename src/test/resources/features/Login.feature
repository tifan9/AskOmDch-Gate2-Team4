@Login
Feature: Registered customer logging in
  As a registered customer,
  I want to log into my account,
  So that I can access my account and make purchases

  Background:
    Given customer is on the accounts page

  Scenario: Verify that registered customer logs in with valid credentials
    When the customer logs in with the credentials:
      | username   | email                | password        |
      | boomer_ang | boomer.ang@gmail.com | boomer.password |
    Then account details page shows username "boomer_ang"

  Scenario: Verify that registered customer logs in with invalid credentials
    When the customer logs in with the credentials:
      | username   | email                | password       |
      | boomer_ang | boomer.ang@gmail.com | wrong.password |
    Then the account page provides error message: "Error: The password you entered for the username boomer_ang is incorrect. Lost your password?"

  Scenario: Verify that registered customer logs in with nonexistent credentials
    When the customer logs in with the credentials:
      | username             | email                 | password     |
      | nonexistent_username | nonexistent@email.com | any.Password |
    Then the account page provides error message: "Error: The username nonexistent_username is not registered on this site. If you are unsure of your username, try your email address instead."

    @ResetPassword
  Scenario: Verify that registered customer resets forgotten password
    When the customer resets password with email "boomer.ang@gmail.com"
    Then the customer receives success message "Password reset email has been sent."