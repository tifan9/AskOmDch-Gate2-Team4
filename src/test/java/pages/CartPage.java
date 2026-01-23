package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class CartPage extends BasePage{

    @FindBy(id = "coupon_code") private WebElement couponFld;
    @FindBy(name = "apply_coupon") private WebElement applyCouponButton;
    private By cartPageMessage = By.cssSelector("div.woocommerce-message");
    private By successMessage = By.cssSelector("div.woocommerce-message, .woocommerce-notices-wrapper .woocommerce-message");
    private By failMessage = By.cssSelector("div.woocommerce-error, .woocommerce-notices-wrapper .woocommerce-error, ul.woocommerce-error li");
    @FindBy(css = ".cart_item, .woocommerce-cart-form__cart-item, tr.cart_item") private List<WebElement> cartItems;
    @FindBy(css = ".product-name a, .cart_item .product-name") private List<WebElement> cartProductNames;
    @FindBy(css = ".qty, input[name*='quantity'], .quantity input") private List<WebElement> quantityInputs;
    @FindBy(css = "button[name='update_cart'], .button[name='update_cart'], input[name='update_cart']") private WebElement updateCartButton;
    @FindBy(css = ".checkout-button") private WebElement proceedToCheckout;


    @FindBy(css = ".cart-subtotal .amount, .order-total .amount")
    private WebElement cartTotalElement;

    @FindBy(css = ".product-price .amount")
    private List<WebElement> itemPrices;

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public double getCartTotal() {
        String totalText = cartTotalElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(totalText);
    }


    public boolean hasProductsDisplayed() {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_item, .woocommerce-cart-form__cart-item, tr.cart_item")));
            return !cartItems.isEmpty();

    }
    public void updateProductQuantity(String productName, int quantity) {
        for (WebElement input : quantityInputs) {
            input.clear();
            input.sendKeys(String.valueOf(quantity));
            break;
        }
        updateCartButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_item, .woocommerce-cart-form__cart-item, tr.cart_item")));
    }
    public void applyCouponcode(String code) {
        couponFld.sendKeys(code);
        applyCouponButton.click();
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(successMessage),
                ExpectedConditions.visibilityOfElementLocated(failMessage)
            ));

    }
    public String successMessage() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public String failMessage() {

            return wait.until(ExpectedConditions.visibilityOfElementLocated(failMessage)).getText();

    }

    public String getMessage() {
        WebElement cartUpdateMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageMessage));
        return cartUpdateMessage.getText();
    }


}