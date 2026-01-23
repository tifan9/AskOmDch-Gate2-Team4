package steps;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.PageFactoryManager;
import pages.StorePage;

import java.util.List;

import static org.testng.Assert.*;

public class AddToCartStepsDefs {
    private WebDriver driver;
    private StorePage storePage;
    private CartPage cartPage;

    @When("the customer updates the product quantity")
    public void the_customer_updates_the_product_quantity() {
        // Implementation for updating product quantity
    }

    @When("I add the following products to the cart:")
    public void iAddTheFollowingProductsToTheCart(DataTable dataTable) {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        List<String> products = dataTable.asList(String.class).subList(1, dataTable.height());
        for (String productName : products) {
            storePage.addProductToCart(productName);
        }
    }


    @Then("the cart should contain these items")
    public void theCartShouldContainTheseItems() {
        cartPage = storePage.clickViewCart();
        assertTrue(cartPage.hasProductsDisplayed(), "Cart should contain products");
    }

    @Given("I have {string} in the cart")
    public void iHaveInTheCart(String productName) {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.addProductToCart(productName);
        cartPage = storePage.clickViewCart();
    }

    @When("I update the quantity of {string} to {int}")
    public void iUpdateTheQuantityOfTo(String productName, int quantity) {
        cartPage.updateProductQuantity(productName, quantity);
    }

    @Then("the cart should reflect the updated quantity")
    public void theCartShouldReflectTheUpdatedQuantity() {
        assertTrue(cartPage.hasProductsDisplayed(), "Cart should contain products");
    }
}