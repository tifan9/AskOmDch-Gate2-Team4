package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

    @FindBy(id = "username") private WebElement usernameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(name = "login") private WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(),'Hello')]") private WebElement loginSuccessMessage;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void fillLoginForm(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }
    public void login(){
        loginButton.click();
    }
    public String getLoginSuccessMessage(){
        return loginSuccessMessage.getText();
    }
}
