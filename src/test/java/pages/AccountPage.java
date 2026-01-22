package pages;

import domainObjects.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

    @FindBy(id = "reg_username") private WebElement regUsernameFld;
    @FindBy(id = "reg_email") private WebElement regEmailFld;
    @FindBy(id = "reg_password") private WebElement regPasswordFld;
    @FindBy(name = "register") private WebElement registerButton;
    @FindBy(id = "username") private WebElement usernameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(name = "login") private WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(),'Hello')]") private WebElement loginSuccessMessage;
    @FindBy(css = ".woocommerce-error>li") private WebElement loginFailMessage;
    @FindBy(css = ".lost_password a") private WebElement forgotPasswordLink;
    @FindBy(id = "user_login") private WebElement forgotPasswordEmailFld;
    @FindBy(xpath = "//button[@type=\"submit\"]") private WebElement resetButton;
    @FindBy(css = ".woocommerce>.woocommerce-message") private WebElement successResetMessage;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(Credentials credentials){
        regUsernameFld.sendKeys(credentials.getUsername());
        regEmailFld.sendKeys(credentials.getEmail());
        regPasswordFld.sendKeys(credentials.getPassword());
    }
    public void register(){ registerButton.click();}

    public void fillLoginForm(Credentials credentials){
        usernameField.sendKeys(credentials.getUsername());
        passwordField.sendKeys(credentials.getPassword());
    }
    public void login(){
        loginButton.click();
    }


    public void clickForgotPassword(){
        forgotPasswordLink.click();
    }
    public void resetPasswordWithEmail(String email){
        forgotPasswordEmailFld.sendKeys(email);
        resetButton.click();
    }
    public String resetPasswordSuccessMessage(){
        return successResetMessage.getText();
    }

    public String getAuthenticationSuccessMessage(){
        return loginSuccessMessage.getText();
    }
    public String getAuthenticationFailMessage(){
        return loginFailMessage.getText();
    }


}
