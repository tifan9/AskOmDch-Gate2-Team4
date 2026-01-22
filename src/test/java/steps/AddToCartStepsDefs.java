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

public class AddToCartStepsDefs {

    private WebDriver driver;
    private StorePage storePage;
    private CartPage cartPage;

    @Given("the customer has product in the cart")
    public void customerHasProductInCart() {
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
        storePage.addProductToCart();
    }

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

        assertEquals("Coupon code applied successfully.", cartPage.successMessage());

    }
}
