package cucusteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import help.pages.LoginPage;
import help.pages.MainPage;
import org.junit.Assert;

public class MainPageSteps {
    //Статический блок инициализации
    static {System.setProperty("webdriver.gecko.driver", "C:\\share\\drivers\\geckodriver.exe");}
    private MainPage mainPage;

        @Then("^he should click on add person button$")
        public void heShouldClickInAddPersonButton(){
        mainPage=new MainPage();
        mainPage.useAddPersonBtn();
        }

    @Then("^he should see add person window$")
    public void heShouldSeeAddPersonWindow() {
        Assert.assertTrue(mainPage.isAddNewPersonDialogVisible());
    }

    @And("^he close add person window$")
    public void heCloseAddPersonWindow(){
        mainPage.useCloseNPD();
        new LoginPage().load();
    }

    @And("^admin logging out$")
    public void adminLoggingOut(){
        mainPage.logout();
    }


    @And("^he should feel laste name field '(.+)'$")
    public void heShouldFeelLasteNameFieldSername(String lastName){
            mainPage.fillLastNameField(lastName);
    }

    @And("^he should feel first name field '(.+)'$")
    public void heShouldFeelFirstNameFieldName(String firstName) {
        mainPage.fillFirstNameField(firstName);
    }

    @Then("^press add new person button$")
    public void pressAddNewPersonButton(){
        mainPage.pressYesBtn();
    }

    @And("^admin must see '(.+)' and '(.+)' at persons cards$")
    public void adminMustSeeSernameAndNameAtPersonsCards(String lastName, String firstName){
        Assert.assertTrue(mainPage.doesPerCardsContainLastNameAndName(lastName,firstName));
    }
}

