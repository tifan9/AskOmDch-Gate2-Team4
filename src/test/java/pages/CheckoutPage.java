package pages;

import domainObjects.BillingDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage{
    private By billingFirstname = By.id("billing_first_name");
    private By billingLastname = By.id("billing_last_name");
    private By billingCountry = By.id("billing_country");
    private By billingStreet = By.id("billing_address_1");
    private By billingTown = By.id("billing_city");
    private By billingState = By.id("billing_state");
    private By billingZip = By.id("billing_postcode");
    private By billingEmail = By.id("billing_email");
    private By placeOrderButton = By.id("place_order");
    private By orderSuccessMessage = By.xpath("//*[@id=\"post-1221\"]/div/div/div/div/div/p");
    @FindBy(id = "billing_first_name") private WebElement failedBillingDetailMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public Select countryDropDown(){
        return new Select(fieldFinder(billingCountry));
    }
    public WebElement fieldFinder(By finder){return driver.findElement(finder);}

    public void fillBillingForm(BillingDetails billingDetails){
        fieldFinder(billingFirstname).sendKeys(billingDetails.getFirstname());
        fieldFinder(billingLastname).sendKeys(billingDetails.getLastname());
        countryDropDown().selectByContainsVisibleText(billingDetails.getCountry());
        fieldFinder(billingStreet).sendKeys(billingDetails.getStreet());
        fieldFinder(billingTown).sendKeys(billingDetails.getTown());
        fieldFinder(billingState).sendKeys(billingDetails.getState());
        fieldFinder(billingZip).sendKeys(billingDetails.getZip());
        fieldFinder(billingEmail).sendKeys(billingDetails.getEmail());

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
        return failedBillingDetailMessage.getText();
    }

}
