package steps;

import constants.EndPoints;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.PageFactoryManager;
import pages.StorePage;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CheckoutStepDefs {

    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private StorePage storePage;

    @Given("I have a product in cart")
    public void i_have_a_product_in_cart() {
        driver = DriverFactory.getDriver();
        checkoutPage = PageFactoryManager.getCheckoutPage(driver);
        checkoutPage.loader(EndPoints.CHECKOUT);
    }

    @Given("I am on the checkout page")
    public void i_am_on_the_checkout_page() {
        checkoutPage.loader2("https://askomdch.com/checkout/");
    }

    @When("I provide billing details")
    public void i_provide_billing_details(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        checkoutPage.fillBillingDetails(data);
    }

    @When("I provide incomplete billing details")
    public void i_provide_incomplete_billing_details(DataTable table) {
        i_provide_billing_details(table);
    }

    @When("I provide different shipping details")
    public void i_provide_different_shipping_details(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        checkoutPage.fillShippingDetails(data);
    }

    @When("I place the order")
    public void i_place_the_order() {
        checkoutPage.placeOrder();
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
//        assertTrue(checkoutPage.getOrderSuccessMessage());
    }

    @Then("I should see validation error messages for required fields")
    public void i_should_see_validation_error_messages_for_required_fields() {
        assertTrue(checkoutPage.isValidationErrorDisplayed());
    }
}
