package steps;

import constants.EndPoints;
import domainObjects.BillingDetails;
import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.PageFactoryManager;
import pages.StorePage;

import static org.testng.Assert.*;

public class CheckoutStepDefs {
    private WebDriver driver;
    private StorePage storePage;
    private CheckoutPage checkoutPage;
    private BillingDetails billingDetails;
    private CartPage cartPage;


    @Given("I have a product in cart")
    public void customerHasProductInCart (){
        driver = DriverFactory.getDriver();
        storePage = PageFactoryManager.getStorePage(driver);
        storePage.loader(EndPoints.STORE);
        storePage.addProductToCart("Anchor Bracelet");

    }

    @And("I am on the checkout page")
    public void guestCustomerIsOnCheckoutPage (){
        cartPage = storePage.clickViewCart();
        checkoutPage = cartPage.proceedToCheckout();
    }

    @And("I provide billing details")
    public void provideCustomerBillingDetails (BillingDetails billingDetails){
        this.billingDetails = billingDetails;
        checkoutPage.fillBillingForm(this.billingDetails);
    }


    @When("I place the order")
    public void guestCustomerCompletesBillingDetailsStage (){
        checkoutPage.placeOrder();
    }


    @Then("the order should be placed successfully")
    public void orderIsPlacedSuccessfully (){
        String result = checkoutPage.placedOrderSuccessMessage();
        assertEquals(result,"Thank you. Your order has been received.", "Successful order message unavailable.");
    }

    @Then("I should see error message for required field: {string}")
    public void billingDetailsErrorMessage(String errorMessage){
        String result = checkoutPage.getFailBillingDetailsMessage();
        assertEquals(result,errorMessage,"Error message not match");
    }

    @And("I provide different shipping details")
    public void iProvideDifferentShippingDetails(BillingDetails billingDetails) {
            this.billingDetails = billingDetails;
            checkoutPage.fillShippingAddressForm(billingDetails);
    }
}
