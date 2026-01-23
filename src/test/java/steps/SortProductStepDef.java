package steps;

import factory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PageFactoryManager;
import pages.StorePage;

import static org.testng.Assert.assertTrue;

public class SortProductStepDef {
    private StorePage storePage;
    private WebDriver driver;

    @When("I select {string} from the sort dropdown")
    public void iSelectFromTheSortDropdown(String sortOption) {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.selectSortOption(sortOption);
    }

    @Then("products should be sorted by {string}")
    public void productsShouldBeSortedBy(String sortOption) {
        assertTrue(storePage.isProductsSortedBy(sortOption), "Products are not sorted by " + sortOption);
    }
}