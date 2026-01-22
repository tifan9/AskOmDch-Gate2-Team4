package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CartPage extends BasePage{

    @FindBy(id = "coupon_code") private WebElement couponFld;
    @FindBy(name = "apply_coupon") private WebElement applyCouponButton;

    private By updateField = By.cssSelector("input.input-text.qty.text");
    private By updateBtn = By.name("update_cart");


//    @FindBy(name = "update_cart") private WebElement updateBtn;
    private By cartPageMessage = By.cssSelector("div.woocommerce-message");

    private By successMessage = By.xpath("//div[@role='alert'] ");
    private By failMessage = By.cssSelector("div.woocommerce-notices-wrapper");



    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void applyCouponcode(String code){
        couponFld.sendKeys(code);
        applyCouponButton.click();

    }
    public String successMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();

    }
    public String failMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(failMessage)).getText();
    }


    public void updateCart() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(updateField));
        element.sendKeys(Keys.ARROW_UP);

        WebElement updateBtnEle = wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
        updateBtnEle.sendKeys(Keys.ARROW_DOWN);

        updateBtnEle.click();

    }
    public String getMessage() {
        WebElement cartUpdateMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageMessage));
        return cartUpdateMessage.getText();


    }

}
