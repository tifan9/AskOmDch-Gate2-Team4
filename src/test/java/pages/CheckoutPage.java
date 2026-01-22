package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class CheckoutPage extends BasePage {

    @FindBy(id = "billing_first_name")
    private WebElement billingFirstName;

    @FindBy(id = "billing_last_name")
    private WebElement billingLastName;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddress;

    @FindBy(id = "billing_city")
    private WebElement billingCity;

    @FindBy(id = "billing_postcode")
    private WebElement billingPostCode;

    @FindBy(id = "billing_email")
    private WebElement billingEmail;

    @FindBy(id = "ship-to-different-address-checkbox")
    private WebElement shipToDifferentAddress;

    @FindBy(id = "shipping_first_name")
    private WebElement shippingFirstName;

    @FindBy(id = "shipping_last_name")
    private WebElement shippingLastName;

    @FindBy(id = "shipping_address_1")
    private WebElement shippingAddress;

    @FindBy(id = "shipping_city")
    private WebElement shippingCity;

    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    @FindBy(css = ".woocommerce-notice--success")
    private WebElement orderSuccessMessage;

    @FindBy(css = ".woocommerce-error")
    private WebElement validationErrorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillBillingDetails(List<Map<String, String>> data) {
        type(billingFirstName, data.get(0).get("firstName"));
        type(billingLastName, data.get(0).get("lastName"));
        type(billingAddress, data.get(0).get("address"));
        type(billingCity, data.get(0).get("city"));
        type(billingPostCode, data.get(0).get("zipCode"));
        type(billingAddress, data.get(0).get("country"));
        type(billingAddress, data.get(0).get("phone"));
        type(billingEmail, data.get(0).get("email"));
    }

    public void fillShippingDetails(Map<String, String> data) {
        shipToDifferentAddress.click();

        type(shippingFirstName, data.get("firstName"));
        type(shippingLastName, data.get("lastName"));
        type(shippingAddress, data.get("address"));
        type(shippingCity, data.get("city"));
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

    public String getOrderSuccessMessage() {
        return orderSuccessMessage.getText();
    }

    public boolean isValidationErrorDisplayed() {
        return validationErrorMessage.isDisplayed();
    }
    private void type(WebElement element, String value) {
        if (value != null && !value.isEmpty()) {
            element.clear();
            element.sendKeys(value);
        }
    }
}
