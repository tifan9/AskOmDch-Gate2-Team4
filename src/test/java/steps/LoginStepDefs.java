package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.PageFactoryManager;

import static org.testng.Assert.*;

import java.util.Map;

public class LoginStepDefs {
    private AccountPage accountPage;
    private WebDriver driver;

    @Given("customer is on the accounts page")
        public void customerHasAccount(){
        driver = DriverFactory.getDriver();
        accountPage = PageFactoryManager.getAccountPage(driver);
        accountPage.loader(EndPoints.ACCOUNT);

    }

    @When("the customer logs in with valid credentials")
    public void theCustomerLogsInWithValidCredentials(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMap(String.class,String.class);

        accountPage.fillLoginForm(credentials.get("username"),credentials.get("password"));
        accountPage.login();

    }

    @Then("account details page shows username {string}")
    public void accountDetailsPageShowsUsername(String username) {
        String result = accountPage.getLoginSuccessMessage();
        assertEquals(result,"Hello "+ username+" (not boomer_ang? Log out)");
    }
}
