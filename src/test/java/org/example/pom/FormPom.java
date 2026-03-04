package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.utilis.utilis;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.text.Utilities;
import java.io.ByteArrayInputStream;

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

    @Step("Set gender")
    public void setGender(String genderParam){
        takeScreenshot("Before we set gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='"+genderParam+"']/../input"));
        gender.click();
        takeScreenshot("After we set gender");
    }

    @Step("Set number")
    public void setNumber(String numberParam){
        takeScreenshot("Before we set number");
        pause(1000);

        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After we set number");
    }

    @Step("Set date")
    public void setDate(String dateParam){
        takeScreenshot("Before we set date");
        pause(1000);

        dateBirthInput.sendKeys(Keys.CONTROL,"a");
        dateBirthInput.sendKeys(dateParam);
        dateBirthInput.sendKeys(Keys.ENTER);
        takeScreenshot("After we set date");
    }

    @Step("Set subject")
    public void setSubject(String subjectParam){
        takeScreenshot("Before we set subject");
        pause(1000);

        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After we set subject");

    }


    @Step("Set state")
    public void setState(String stateParam){
        takeScreenshot("Before we set state");
        scrollToElement(state);
        pause(1000);
        state.click();
        WebElement stateOption = state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        stateOption.click();
        takeScreenshot("After we set state");

    }

    @Step("Set city")
    public void setCity(String cityParam){
        takeScreenshot("Before set city");
        city.click();
        WebElement cityOption = city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
        cityOption.click();
        takeScreenshot("After we set city");

    }

    @Step("Set hobbiers")
    public void setHobbies(String hobbyParam){
        takeScreenshot("Before we set hobbies");
        WebElement ddState = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='"+ hobbyParam + "']//../input"));
        ddState.sendKeys(" ");
        takeScreenshot("After we set hobbies");
    }

    @Step("Click submit")
    public void clickSubmit(){
        takeScreenshot("Before click submit");
        submit.click();
        pause(1000);
        takeScreenshot("After click submit");
    }

    @Step("Get table data")
    public String getTableData(String labelParam){
        takeScreenshot("Before get table data for: " + labelParam);
        String value = driver.findElement(By.xpath("//table//*[text()='"+ labelParam +"']/../*[2]")).getText();
        takeScreenshot("After get table data for: " + labelParam);
        return value;
    }

    @Step("Set first name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before set first name");

        pause(1000);
        utilis.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm),80);

        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After we set first name");
    }

    @Step("Set last name")
    public void setLastName(String lastNameParam) {
        takeScreenshot("Before set last name");
        pause(1000);
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After we set last name");
    }

    @Step("Set user email")
    public void setUserEmail(String userEmailParam) {
        takeScreenshot("Before set user email");
        pause(1000);
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
        takeScreenshot("After we set user email");
    }

    @Step("Click forms")
    public void clickForms(){
        takeScreenshot("Before click forms");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",forms);
        forms.click();
        takeScreenshot("After click forms");
    }

    @Step("Click practice form")
    public void clickPracticeForm(){
        takeScreenshot("Before click practice form");
        utilis.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm),7);
        practiceForm.click();
        takeScreenshot("After click practice form");
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

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }



}
//mvn allure::serve - raport