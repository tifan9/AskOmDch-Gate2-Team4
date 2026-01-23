package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.PageFactoryManager;
import pages.StorePage;

import static org.testng.Assert.assertEquals;

public class ApplyCouponStepDefs {

    private WebDriver driver;
    private StorePage storePage;
    private CartPage cartPage;

    @Given("the customer has product in the cart")
    public void customerHasProductInCart() {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
        // Add a default product to cart - use a product that's available
        storePage.addProductToCart("Black Over-the-shoulder Handbag");
    }

    @And("the customer is on the cart page")
    public void customerIsOnCartPage() {
        cartPage = storePage.clickViewCart();
    }

    @When("the customer applies the coupon {string}")
    public void customerAppliesCoupon(String coupon) {
        cartPage.applyCouponcode(coupon);
    }

    @Then("the coupon should be applied successfully")
    public void theCouponShouldBeAppliedSuccessfully() {
        assertEquals(cartPage.successMessage(), "Coupon code applied successfully.");
    }

    @Then("the coupon should not be applied")
    public void theCouponShouldNotBeAppliedSuccessfully() {
        assertEquals(cartPage.failMessage(), "Coupon \"off30\" does not exist!");
    }

    @Then("the cart should be updated successfully")
    public void theCartShouldBeUpdatedSuccessfully() {
        String result = cartPage.getMessage();
        assertEquals(result, "Cart updated.");
    }
}
