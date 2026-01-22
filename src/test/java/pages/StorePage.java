package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }
    private By viewLink = By.cssSelector("a.added_to_cart.wc-forward");

    public void addProductToCart() {
        By productName = By.cssSelector("a[aria-label='Add “Black Over-the-shoulder Handbag” to your cart']");
        driver.findElement(productName).click();

    }

    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewLink)).click();

        return PageFactoryManager.getCartPage(driver);
    }


}
