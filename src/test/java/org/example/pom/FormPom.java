package org.example.pom;

import org.example.utilis.utilis;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.text.Utilities;

public class FormPom {
    static public WebDriver driver;
    static public JavascriptExecutor js;


    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateBirthInput;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;
    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driverParam){
        driver = driverParam;
        js=(JavascriptExecutor) driverParam;
        PageFactory.initElements(driver, this);

    }

    public void setGender(String genderParam){
         WebElement gender=   driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='"+genderParam+"']/../input"));
         gender.click();
    }

    public void setNumber(String numberParam){
        pause(1000);

        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setDate(String dateParam){
        pause(1000);

        dateBirthInput.sendKeys(Keys.CONTROL,"a");
        dateBirthInput.sendKeys(dateParam);
        dateBirthInput.sendKeys(Keys.ENTER);

    }

    public void setSubject(String subjectParam){
        pause(1000);

        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);

    }



        public void setState(String stateParam){
        scrollToElement(state);
        pause(1000);
        state.click();
        WebElement stateOption=  state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        stateOption.click();

        }

        public void setCity(String cityParam){
            city.click();
            WebElement cityOption= city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
            cityOption.click();

        }

    public void setHobbies(String hobbyParam){
        WebElement ddState=  driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='"+ hobbyParam + "']//../input"));
        ddState.sendKeys(" ");
    }
public void clickSubmit(){
        submit.click();
        pause(1000);
}

public String getTableData(String labelParam){
       String value= driver.findElement(By.xpath("//table//*[text()='"+ labelParam +"']/../*[2]")).getText();
        return value;

}


    public void setFirstName(String firstNameParam) {

        pause(1000);
        utilis.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm),80);

        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParam) {
        pause(1000);
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setUserEmail(String userEmailParam) {
        pause(1000);
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }



    public void clickForms(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",forms);
        forms.click();
    }

    public void clickPracticeForm(){
        utilis.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm),7);
        practiceForm.click();
    }

    public void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }


    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }

}
