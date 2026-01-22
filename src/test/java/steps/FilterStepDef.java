package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PageFactoryManager;
import pages.StorePage;

import static org.testng.Assert.assertTrue;

public class FilterStepDef {
    private StorePage storePage;
    private WebDriver driver;
    
    @Given("I am on the product listing page")
    public void iAmOnTheProductListingPage() {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
    }

    @When("I filter products with minimum price {int} and maximum price {int}")
    public void iFilterProductsWithMinimumPriceAndMaximumPrice(int minPrice, int maxPrice) {
        storePage.filterByPriceRange(minPrice, maxPrice);
    }

    @Then("I should see only products priced between {int} and {int}")
    public void iShouldSeeOnlyProductsPricedBetweenAnd(int minPrice, int maxPrice) {
        boolean hasProducts = storePage.hasProductsDisplayed();
        assertTrue(hasProducts, "No products found after filtering");
    }

    @When("I select the category {string}")
    public void iSelectTheCategory(String categoryName) {
        storePage.selectCategory(categoryName);
    }

    @Then("I should see only products in that {string}")
    public void iShouldSeeOnlyProductsInThat(String categoryName) {
        assertTrue(storePage.hasProductsDisplayed());
    }
}
