package cucusteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import help.pages.LoginPage;
import help.pages.MainPage;
import org.junit.Assert;

public class LoginPageSteps {
    private LoginPage loginPage;
    private MainPage mainPage;

    //Статический блок инициализации
    static {System.setProperty("webdriver.gecko.driver", "C:\\share\\drivers\\geckodriver.exe");}

    //Помечаем метод аннотацией и передаем в нее регулярку. Все что регулярка "вытащит" между скобок будет
    // передано вкаестве строкового аргумента
    @Given("^user navigates to '(.+)'$")
    public void openLoginPage(String url) {
        loginPage = new LoginPage().load();
    }


    @When("^he fill login field with '(.+)'$")
    public void heFillLoginFieldWithAdmin(String text)  {
        loginPage.fillLoginField(text);
    }

    @And("^he fill password field with '(.+)'$")
    public void heFillPasswordFieldWithAsd(String text) {
        loginPage.fillPasswordField(text);
    }

    @And("^he click submit button$")
    public void submit(){
        loginPage.submit();
    }

    @Then("^he should see error message$")
    public void checkForErrorMsg() {
        Assert.assertTrue(loginPage.isErrorMessageVisible());
    }
    @Then("^he should see '(.+)' in profile box$")
    public void checkUsername(String username) {
        mainPage = new MainPage();
        Assert.assertEquals(username, mainPage.getUsername());
    }

    @And("^user logging out$")
    public void logout() {mainPage.logout();}

}