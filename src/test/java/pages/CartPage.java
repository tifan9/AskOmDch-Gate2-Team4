package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage{
    @FindBy(css = ".cart_item, .woocommerce-cart-form__cart-item, tr.cart_item") private List<WebElement> cartItems;
    @FindBy(css = ".product-name a, .cart_item .product-name") private List<WebElement> cartProductNames;
    @FindBy(css = ".qty, input[name*='quantity'], .quantity input") private List<WebElement> quantityInputs;
    @FindBy(css = "button[name='update_cart'], .button[name='update_cart'], input[name='update_cart']") private WebElement updateCartButton;
    @FindBy(id = "coupon_code") private WebElement couponFld;
    @FindBy(name = "apply_coupon") private WebElement applyCouponButton;
    @FindBy(css = ".cart-subtotal .amount, .order-total .amount") private WebElement cartTotalElement;
    @FindBy(css = ".checkout-button") private WebElement proceedToCheckout;

    @FindBy(css = ".product-price .amount")
    private List<WebElement> itemPrices;
    private By successMessage = By.xpath("//div[@role='alert'] ");

    public double getCartTotal() {
        String totalText = cartTotalElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(totalText);
    }

    public double calculateSumOfItems() {
        return itemPrices.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replaceAll("[^0-9.]", "")))
                .sum();
    }

    public boolean isProductInCart(String productName) {
        return cartProductNames.stream().anyMatch(item -> item.getText().toLowerCase().contains(productName.toLowerCase()));
    }
    public void applyCouponcode(String code){
        couponFld.sendKeys(code);
        applyCouponButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

    }
    public String successMessage(){
        return driver.findElement(successMessage).getText();
    }
    
    public boolean hasProductsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_item, .woocommerce-cart-form__cart-item, tr.cart_item")));
            return !cartItems.isEmpty();
        } catch (Exception e) {
            return false;
        }
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
    public CheckoutPage proceedToCheckout(){
        proceedToCheckout.click();
        return PageFactoryManager.getCheckoutPage(driver);
    }
    
    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
