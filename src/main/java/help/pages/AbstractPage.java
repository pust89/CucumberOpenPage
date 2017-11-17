package help.pages;

import helpers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
     protected WebDriver driver;

             public AbstractPage() {
               driver = DriverManager.getDriver();
                PageFactory.initElements(driver, this);
            }
 }