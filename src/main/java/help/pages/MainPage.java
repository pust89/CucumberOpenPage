package help.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends AbstractPage {

     @FindBy(css = "#profile span")
     private WebElement usernameContainer;

     @FindBy(css = "#logout")
     private WebElement logoutBtn;

     @FindBy(css = "#add-person")
     private WebElement addPersonBtn;

     @FindBy(id="person-dialog")
     private WebElement addNewPersonDialog;

     @FindBy (xpath = "/html/body/div[3]/div[1]/button/span[2]")
     private WebElement closeNPDBtn;

    @FindBy(css="#person-last-name")
    WebElement personLastNameField;

    @FindBy(css="#person-first-name")
    WebElement personFirstNameField;

    @FindBy(css=".yes")
    WebElement yesBtn;

    @FindBy(xpath = "//*[@id=\"persons\"]")
    WebElement personsCards;

    public String getUsername() {

        return usernameContainer.getText();
    }

    public void logout(){
        logoutBtn.click();
    }

    public void useAddPersonBtn(){
        addPersonBtn.click();
    }

    public boolean isAddNewPersonDialogVisible(){
        return(addNewPersonDialog.isDisplayed());
    }
    public void useCloseNPD(){
        closeNPDBtn.click();
    }

    public MainPage fillLastNameField(String text){
        personLastNameField.sendKeys(text);
        return this;
    }
    public MainPage fillFirstNameField(String text){
        personFirstNameField.sendKeys(text);
        return this;
    }
    public void pressYesBtn(){
        yesBtn.click();
    }

    public boolean doesPerCardsContainLastNameAndName(String lastName,String FirstName){
        if(personsCards.getText().contains(lastName) && personsCards.getText().contains(lastName)){
            return  true; }
            else{return false;}
    }


 }