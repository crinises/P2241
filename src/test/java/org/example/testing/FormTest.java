package org.example.testing;

import org.example.pom.FormPom;
import org.example.utilis.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTest {

    static public WebDriver driver;


    static public String URL="https://demoqa.com/";

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

//pentru test chiar
    @Test
    public void formTest(){
        System.out.println("Start testare");
        driver.get(URL);

        FormPom formPom= new FormPom(driver);

        formPom.clickForms();

        System.out.println("Finish test");




    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
