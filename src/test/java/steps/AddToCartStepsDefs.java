package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.PageFactoryManager;
import pages.StorePage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartStepsDefs {
    private WebDriver driver;
    private StorePage storePage;
    private CartPage cartPage;


    @And("the customer is on the cart page")
    public void customerIsOnCartPage(){
        cartPage = storePage.clickViewCart();
    }
    @When("the customer applies the coupon {string}")
    public void customerAppliesCoupon(String coupon){
        cartPage.applyCouponcode(coupon);
    }

    @Then("the coupon should be applied successfully")
    public void i_should_be_redirected_to_the_cart_page() {

        assertEquals(cartPage.successMessage(), "Coupon code applied successfully.");

    }

    @When("the customer updates the product quantity")
    public void the_customer_updates_the_product_quantity() {

    }
    @Then("the cart should be updated successfully")
    public void the_cart_should_be_updated_successfully() {

    }
    
    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.addProductToCart(productName);
    }

    @Then("the product should be added to the cart successfully")
    public void theProductShouldBeAddedToTheCartSuccessfully() {
        assertTrue(storePage.isProductAddedToCart(), "Product was not added to cart successfully");
    }

    @Then("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        cartPage = storePage.clickViewCart();
        assertTrue(cartPage != null, "Failed to navigate to cart page");
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

    @And("the cart total should be updated accordingly")
    public void theCartTotalShouldBeUpdatedAccordingly() {
        double actualTotal = cartPage.getCartTotal();
        assertTrue(actualTotal > 0, "Cart total should be greater than 0");
    }

    @And("the cart total should reflect the sum of all item prices")
    public void theCartTotalShouldReflectTheSumOfAllItemPrices() {
        double actualTotal = cartPage.getCartTotal();
        double expectedSum = cartPage.calculateSumOfItems();
        assertEquals(actualTotal, expectedSum, 0.01, "The cart total does not match the sum of items!");
    }
}
