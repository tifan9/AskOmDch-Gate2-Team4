package pages;

import domainObjects.BillingDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;

public class CheckoutPage extends BasePage{

    private By billingFirstname = By.id("billing_first_name");
    private By billingLastname = By.id("billing_last_name");
    @FindBy(id = "billing_country") private WebElement billingCountry;
    private By billingStreet = By.id("billing_address_1");
    private By billingTown = By.id("billing_city");
    private By billingState = By.id("billing_state");
    private By billingZip = By.id("billing_postcode");
    private By billingEmail = By.id("billing_email");
    private By placeOrderButton = By.id("place_order");
    private By orderSuccessMessage = By.xpath("//*[@id=\"post-1221\"]/div/div/div/div/div/p");
    @FindBy(css = ".woocommerce-error>li") private WebElement failedBillingDetailMessage;

    @FindBy(id = "ship-to-different-address-checkbox") private WebElement shipToDifferentAddressCheckbox;
    @FindBy(id = "shipping_first_name") private WebElement shippingFirstname;
    @FindBy(id = "shipping_last_name") private WebElement shippingLastname;
    @FindBy(id = "shipping_country") private WebElement shippingCountry;
    @FindBy(id = "shipping_address_1") private WebElement shippingStreet;
    @FindBy(id = "shipping_city") private WebElement shippingTown;
    @FindBy(id = "shipping_state") private WebElement shippingState;
    @FindBy(id = "shipping_postcode") private WebElement shippingZip;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public Select countryDropDown(WebElement countryField){
        return new Select(countryField);
    }
    public WebElement fieldFinder(By finder){return driver.findElement(finder);}

    public void fillBillingForm(BillingDetails billingDetails){
        fieldFinder(billingFirstname).sendKeys(Optional.ofNullable(billingDetails.getFirstname()).orElse(""));
        fieldFinder(billingLastname).sendKeys(Optional.ofNullable(billingDetails.getLastname()).orElse(""));
        countryDropDown(billingCountry).selectByContainsVisibleText(Optional.ofNullable(billingDetails.getCountry()).orElse(""));
        fieldFinder(billingStreet).sendKeys(Optional.ofNullable(billingDetails.getStreet()).orElse(""));
        fieldFinder(billingTown).sendKeys(Optional.ofNullable(billingDetails.getTown()).orElse(""));
        fieldFinder(billingState).sendKeys(Optional.ofNullable(billingDetails.getState()).orElse(""));
        fieldFinder(billingZip).sendKeys(Optional.ofNullable(billingDetails.getZip()).orElse(""));
        fieldFinder(billingEmail).sendKeys(Optional.ofNullable(billingDetails.getEmail()).orElse(""));

    }
    public void placeOrder(){
        wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderButton));
        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('place_order').click();"
        );
    }
    public String placedOrderSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage));
        return driver.findElement(orderSuccessMessage).getText();
    }
    public String getFailBillingDetailsMessage(){
        wait.until(ExpectedConditions.visibilityOf(failedBillingDetailMessage));
        return failedBillingDetailMessage.getText();
    }


    public void fillShippingAddressForm(BillingDetails billingDetails){
        shipToDifferentAddressCheckbox.click();
        shippingFirstname.sendKeys(Optional.ofNullable(billingDetails.getFirstname()).orElse(""));
        shippingLastname.sendKeys(Optional.ofNullable(billingDetails.getLastname()).orElse(""));
        countryDropDown(shippingCountry).selectByContainsVisibleText(Optional.ofNullable(billingDetails.getCountry()).orElse(""));
        shippingStreet.sendKeys(Optional.ofNullable(billingDetails.getStreet()).orElse(""));
        shippingTown.sendKeys(Optional.ofNullable(billingDetails.getTown()).orElse(""));
        shippingZip.sendKeys(Optional.ofNullable(billingDetails.getZip()).orElse(""));
        shippingState.sendKeys(Optional.ofNullable(billingDetails.getState()).orElse(""));

    }

}
