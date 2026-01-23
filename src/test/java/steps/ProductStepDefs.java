package steps;

import constants.EndPoints;
import factory.DriverFactory;
import static org.testng.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.PageFactoryManager;
import pages.StorePage;


public class ProductStepDefs {
    private StorePage storePage;
    private WebDriver driver;

    @Given("I am on the Store Page")
    public void iAmOnTheStorePage() {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
    }

    @When("I select the {string}")
    public void iSelectTheProduct(String productName) {
        storePage.viewProductDetails(productName);
    }

    @Then("I should see the full product information for {string}")
    public void iShouldSeeFullProductInformation(String productName) {
        assertTrue(storePage.isProductTitleVisible(), "Title missing!");
        assertTrue(storePage.isProductImageVisible(), "Image missing!");
        assertTrue(storePage.isProductPriceVisible(), "Price missing!");
        assertTrue(storePage.isAddToCartButtonVisible(), "Cart button missing!");

    }
}
