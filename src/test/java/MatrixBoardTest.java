//-import org.junit.After;
      //  -import org.junit.Assert;
      //  -import org.junit.Before;
     //   -import org.junit.Test;
import helpers.DriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MatrixBoardTest {
static WebDriver driver; //Поле для хранения нашего драйвера
static WebDriverWait wait; //поле для хранияния нашего Explicit Wait

public static final String USER_NAME = "admin";
public static final String PASSWORD = "admin";

 @BeforeClass //Аннотация Junit. Говорит, что метод должен запускаться каждый раз после создания экземпляра класса, перед всеми тестами
    public static void setUp() {
                //Устанавливаем System Property, чтобы наше приложени смогло найти драйвер
                        System.setProperty("webdriver.gecko.driver", "C:\\share\\drivers\\geckodriver.exe");
                //Инициализируем драйвер
                 //driver = new FirefoxDriver();
     driver = DriverManager.getDriver();
                //Инициализируем Implicit Wait

     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                //инициализируем Explicit Wait
     wait = new WebDriverWait(driver, 10);
     }

     @AfterClass //Аннотация Junit. Говорит, что метод должен запускаться каждый раз после всех тестов
     public static void tearDown() {
     driver.quit();
     }

     @Test //Аннотация Junit. Говорит, что этот метод - тестовый
     public void PositiveLoginAdminAccountTest(){

      driver.navigate().to("http://at.pflb.ru/matrixboard2/"); //перейти по URL
         WebElement loginField = driver.findElement(By.id("login-username"));
         WebElement passwordField = driver.findElement(By.id("login-password"));
         WebElement submitBtn = driver.findElement(By.id("login-button"));
      //Заполним поля текстом
        loginField.sendKeys(USER_NAME);
        passwordField.sendKeys(PASSWORD);
      //отправим форму
        submitBtn.click();

        WebElement usernameContainer = driver.findElement(By.cssSelector("#profile > a:nth-child(1) > span:nth-child(1)"));
         Assert.assertEquals(USER_NAME, usernameContainer.getText());
         if(USER_NAME.equals(usernameContainer.getText())){
             System.out.println("Enter with login: admin; password: admin; was successful");
             System.out.println("PositiveLoginAdminAccountTest was PASSED");
                             }
                else { System.out.println("Enter with login: admin; password: admin; was failure!!!");
                        System.out.println("PositiveLoginAdminAccountTest was FAILED");
                             }

         //НЕОБХОДИМО НАЙТИ И НАЖАТЬ КНОПКУ ВЫХОДА ДЛЯ УСПЕШНОГО НЕГАТИВНОГО ТЕСТА!!!!
         WebElement logoutBtn = driver.findElement(By.id("logout"));
         logoutBtn.click();
     }



    @Test //Аннотация Junit. Говорит, что этот метод - тестовый
    public void AdminRightsTest(){
        driver.navigate().to("http://at.pflb.ru/matrixboard2/"); //перейти по URL
        WebElement loginField = driver.findElement(By.id("login-username"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement submitBtn = driver.findElement(By.id("login-button"));
        //Заполним поля текстом
        loginField.sendKeys(USER_NAME);
        passwordField.sendKeys(PASSWORD);
        //отправим форму
        submitBtn.click();

        WebElement addPersonBtn = driver.findElement(By.id("add-person"));// админская кнопка "добавить человека"
        addPersonBtn.click();
        WebElement personDialog = driver.findElement(By.id("person-dialog"));// форма добавления нового человека
        if(personDialog.isDisplayed()){
            System.out.println("Enter with login: admin; password: admin; was successful");
            System.out.println("Account admin has admin's rights!");
            System.out.println("AdminRightsTest was PASSED");
                            }
        else{
            System.out.println("Account admin has'not admin's rights!");
            System.out.println("Or!!!!Enter with login: admin; password: admin; was failure");
            System.out.println("AdminRightsTest was FAILED");
        }
        //Закрываем окно добавления нового человека
        //НИКАК не могу подобрать id или css к кнопке закрывания окна добавления нового человека(((
        WebElement closeWindowBtn = driver.findElement(By.className("ui-button-icon-primary ui-icon ui-icon-closethick"));
        closeWindowBtn.click();

        //НЕОБХОДИМО НАЙТИ И НАЖАТЬ КНОПКУ ВЫХОДА ДЛЯ УСПЕШНОГО НЕГАТИВНОГО ТЕСТА!!!!

        WebElement logoutBtn = driver.findElement(By.id("logout"));
        logoutBtn.click();

    }
    @Test
    public void NegativeLoginAdminAccountTest(){

        driver.navigate().to("http://at.pflb.ru/matrixboard2/"); //перейти по URL
        WebElement loginField = driver.findElement(By.id("login-username"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement submitBtn = driver.findElement(By.id("login-button"));
        //Заполним поля текстом
        loginField.sendKeys(USER_NAME);
        passwordField.sendKeys("badPass");
        //отправим форму
        submitBtn.click();

        WebElement loginFailedMassage = driver.findElement(By.cssSelector("#login-failed"));
        Assert.assertTrue(loginFailedMassage.isDisplayed());
        if(loginFailedMassage.isDisplayed()){
            System.out.println("Enter with login: admin; password: badPass; was failure");
            System.out.println("Login failed massage "+"Неправильный логин или пароль"+"is displayed");
            System.out.println("NegativeLoginAdminAccountTest was PASSED");
        }
        else { System.out.println("Enter with login: admin; password: badPass; was successful!!!");
            System.out.println("OR Login failed massage "+"Неправильный логин или пароль"+"is not displayed");
            System.out.println("NegativeLoginAdminAccountTest was FAILED");
        }
    }



 }