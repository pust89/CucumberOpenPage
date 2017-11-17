package help.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

        public class LoginPage extends AbstractPage {
     //@FindBy(id = "login-username")
             @FindBy(css = "#login-username")
     private WebElement loginField;

             //@FindBy(id = "login-password")
             @FindBy(xpath = "//input[@id='login-password']")
     private WebElement passwordField;

             @FindBy(id = "login-button")
     private WebElement submitBtn;

             @FindBy(id = "login-failed")
     private WebElement messageContainer;

             private static final String url = "http://at.pflb.ru/matrixboard2/";
            public LoginPage load(){
                driver.navigate().to(url);
                return this;
            }
                public LoginPage fillLoginField(String text) {
               loginField.sendKeys(text);
                return this;
            }

             public LoginPage fillPasswordField(String text) {
                passwordField.sendKeys(text);
                return this;
            }

             public void submit() {
                submitBtn.click();
            }

             public boolean isErrorMessageVisible(){
                return messageContainer.isDisplayed();
            }
 }