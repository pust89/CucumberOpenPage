
import help.pages.LoginPage;
import help.pages.MainPage;
import helpers.DriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MatrixBoardTest {
static WebDriver driver; //Поле для хранения нашего драйвера
static WebDriverWait wait; //поле для хранияния нашего Explicit Wait

public static final String USER_NAME = "admin";
public static final String PASSWORD = "admin";

 @BeforeClass
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

     @AfterClass
     public static void tearDown() {
     driver.quit();
     }
    @Test
    public void NegativeLoginAdminAccountTest(){
        LoginPage loginPage = new LoginPage();
        loginPage.load().fillLoginField(USER_NAME).fillPasswordField("badPass").submit();
        WebElement loginFailedMassage = driver.findElement(By.cssSelector("#login-failed"));
        Assert.assertTrue(loginFailedMassage.isDisplayed());
        if(loginFailedMassage.isDisplayed()){
            System.out.println("Enter with login: admin; password: badPass; was failure\nLogin failed massage \"+\"\\\"Неправильный логин или пароль\\\"+\"+\"is displayed" +
                    "\nNegativeLoginAdminAccountTest was PASSED"); }
        else { System.out.println("Enter with login: admin; password: badPass; was successful!!!\nOR Login failed massage \"+\"Неправильный логин или пароль\"+\"is not displayed" +
                "\nNegativeLoginAdminAccountTest was FAILED"); }
    }

     @Test
     public void PositiveLoginAdminAccountTest(){
         LoginPage loginPage = new LoginPage();
         loginPage.load().fillLoginField(USER_NAME).fillPasswordField(PASSWORD).submit();
         MainPage mainPage = new MainPage();
         mainPage.getUsername();
         Assert.assertEquals(USER_NAME, mainPage.getUsername());
         if(USER_NAME.equals(mainPage.getUsername())){
             System.out.println("Enter with login: admin; password: admin; was successful\nPositiveLoginAdminAccountTest" +
                     " was PASSED"); }
                else { System.out.println("Enter with login: admin; password: admin; was failure!\nPositiveLoginAdmin" +
                 "AccountTest was FAILED"); }
         //Без перегрузки страницы не разлогиневается
         loginPage.load();
         //Написал отдельный метод в классе MainPage для разлогина
         mainPage.logout();

     }

    @Test
    public void AdminRightsTest(){
        LoginPage loginPage = new LoginPage();
        loginPage.load().fillLoginField(USER_NAME).fillPasswordField(PASSWORD).submit();
        WebElement addPersonBtn = driver.findElement(By.cssSelector("#add-person"));// админская кнопка "добавить человека"
        addPersonBtn.click();
        WebElement addNewPersonDialog = driver.findElement(By.id("person-dialog"));// форма добавления нового человека
        Assert.assertTrue(addNewPersonDialog.isDisplayed());
        if(addNewPersonDialog.isDisplayed()){
            System.out.println("Enter with login: admin; password: admin; was successful\nAccount admin has admin's rights!" +
                    "\nAdminRightsTest was PASSED"); }
        else{ System.out.println("Account admin has'not admin's rights!\nOr!!!!Enter with login: admin; password: admin; was failure" +
                "\nAdminRightsTest was FAILED"); }

        //Закрываем окно добавления нового человека

        WebElement closeWindowBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/button/span[2]"));
        closeWindowBtn.click();
        //Без перегрузки страницы не разлогиневается
         loginPage.load();
        //Разлогинемся
        new MainPage().logout();

    }

    @Test
    public void AddPersonTest(){
        LoginPage loginPage = new LoginPage();
        loginPage.load().fillLoginField(USER_NAME).fillPasswordField(PASSWORD).submit();
        WebElement addPersonBtn = driver.findElement(By.id("add-person"));// админская кнопка "добавить человека"
        addPersonBtn.click();
        //Создаем уникальную пару Имя+Фамилия
        Random random = new Random();
        int r = random.nextInt(50);
        String personLastName = "Sername"+ r;
        String personFirstName = "Name"+ r;
        String fullName = personLastName+" "+personFirstName;
        //Добавляем нового человека
        WebElement personLastNameField = driver.findElement(By.cssSelector("#person-last-name"));
        personLastNameField.sendKeys(personLastName);
        WebElement personFirstNameField = driver.findElement(By.cssSelector("#person-first-name"));
        personFirstNameField.sendKeys(personFirstName);

        WebElement yesBtn = driver.findElement(By.cssSelector(".yes"));//(By.xpath("/html/body/div[3]/div[3]/div/button/span[2]"))

        yesBtn.click();
        //Получаем все карточки на странице

        WebElement personsCards = driver.findElement(By.xpath("//*[@id=\"persons\"]"));

        Assert.assertTrue(personsCards.getText().contains(fullName));

        if(personsCards.getText().contains(personLastName)){
            System.out.println("AddPersonTest was PASS");}
           else{System.out.println("AddPersonTest was FAIL");}
        //Без перегрузки страницы не разлогиневается
        loginPage.load();
        new MainPage().logout();
    }

 }