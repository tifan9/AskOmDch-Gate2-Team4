package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CartPage extends BasePage{

    @FindBy(id = "coupon_code") private WebElement couponFld;
    @FindBy(name = "apply_coupon") private WebElement applyCouponButton;

    private By successMessage = By.xpath("//div[@role='alert'] ");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void applyCouponcode(String code){
        couponFld.sendKeys(code);
        applyCouponButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

    }
    public String successMessage(){
        return driver.findElement(successMessage).getText();
    }
}
