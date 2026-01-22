package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PageFactoryManager;
import pages.StorePage;

import static org.testng.Assert.assertTrue;

public class SearchProductStepDef {
    private StorePage storePage;
    private WebDriver driver;
    
    @Given("I am on the store page")
    public void iAmOnTheStorePage() {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
    }

    @When("I search for {string}")
    public void iSearchFor(String keyword) {
        storePage.searchForProduct(keyword);
        storePage.clickSearchButton();
    }


    @Then("I should see a list of products related to {string}")
    public void iShouldSeeAListOfProductsRelatedTo(String keyword) {
        assertTrue(storePage.hasProductsDisplayed());
    }

    @Then("I should see a message indicating no products were found")
    public void iShouldSeeAMessageIndicatingNoProductsWereFound() {
        assertTrue(storePage.hasNoResultsMessage());
    }
}
