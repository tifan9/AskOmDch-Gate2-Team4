package steps;

import constants.EndPoints;
import domainObjects.Credentials;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.PageFactoryManager;

import static org.testng.Assert.*;

public class AuthenticationStepDefs {
    private AccountPage accountPage;
    private WebDriver driver;
    private Credentials credentials;

    @Given("customer is on the accounts page")
        public void customerHasAccount(){
        driver = DriverFactory.getDriver();
        accountPage = PageFactoryManager.getAccountPage(driver);
        accountPage.loader(EndPoints.ACCOUNT);

    }

    @When("the customer logs in with the credentials:")
    public void theCustomerLogsInWithValidCredentials(Credentials credentials) {
        this.credentials = credentials;
        accountPage.fillLoginForm(this.credentials);
        accountPage.login();

    }

    @Then("account details page shows username {string}")
    public void accountDetailsPageShowsUsername(String username) {
        String result = accountPage.getAuthenticationSuccessMessage();
        assertEquals(result,"Hello "+ username+" (not "+username+"? Log out)");
    }

    @Then("the account page provides error message: {string}")
    public void theAccountPageShowsErrorMessage(String message) {
        String result = accountPage.getAuthenticationFailMessage();
        assertEquals(result,message,"Error message not matched");
    }

    @When("the customer resets password with email {string}")
    public void theCustomerCompletesResetProcess(String email) {
        accountPage.clickForgotPassword();
        accountPage.resetPasswordWithEmail(email);
    }

    @Then("the customer receives success message {string}")
    public void theCustomerReceivesSuccessMessage(String message) {
        String result = accountPage.resetPasswordSuccessMessage();
        assertEquals(result,message,"Success Message is inaccurate");
    }

    //Registration-specific defs

    @When("the customer registers with the credentials:")
    public void theCustomerRegistersWithTheCredentials(Credentials credentials) {
        if(this.credentials == null) this.credentials = credentials;
        accountPage.fillRegistrationForm(this.credentials);
        accountPage.register();
    }
}
