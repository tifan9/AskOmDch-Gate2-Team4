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


    @Then("the coupon successfully message should be displayed")
    public void theCouponSuccessfullyMessageShouldBeDisplayed() {
        String result = cartPage.getMessage();
        assertEquals(result, "Coupon code applied successfully.");
    }





    @Then("an error message should be displayed")
    public void anErrorMessageCouponDoesNotExistShouldBeDisplayed() {
        String result = cartPage.failMessage();
        assertEquals(result, "Coupon \"off30\" does not exist!");
    }
}
