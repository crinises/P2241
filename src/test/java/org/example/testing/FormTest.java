package org.example.testing;

import org.example.pom.FormPom;

import org.example.utilis.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FormTest {
    static public WebDriver driver;

    static public String URL = "https://demoqa.com";
    static public String FIRST_NAME = "Gîlca";
    static public String LAST_NAME = "Cristina";
    static public String EMAIL = "cristinagilca@gmail.com";
    static public String GENDER="Female";
    static public String NUMBER = "0123456789";
    static public String DATE = "30 Mar 2006";
    static public String SUBJECT = "Maths";
    static public String HOBBY = "Sports";
    static public String STATE = "Haryana";
    static public String CITY = "Panipat";





    @BeforeMethod
    public void BeforeMethod() throws MalformedURLException {
//        driver = Driver.getAutoLocalDriver();
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest(){
        System.out.println("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.pause(3000);
        formPom.clickForms();
        formPom.clickPracticeForm();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setUserEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobbies(HOBBY);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.pause(3000);
        formPom.clickSubmit();
        formPom.pause(3000);
        String actualName= formPom.getTableData("Student Name");
        Assert.assertEquals(actualName,FIRST_NAME +" "+LAST_NAME);
        String actualEmail = formPom.getTableData("Student Email");
        Assert.assertEquals(actualEmail, EMAIL, "Email nu coincide!");
        String actualGender = formPom.getTableData("Gender");
        Assert.assertEquals(actualGender, GENDER, "Gender nu coincide!");
        String actualMobile = formPom.getTableData("Mobile");
        Assert.assertEquals(actualMobile, NUMBER, "Mobile nu coincide!");
        String actualSubjects = formPom.getTableData("Subjects");
        Assert.assertEquals(actualSubjects, SUBJECT, "Subjects nu coincide!");
        String actualHobbies = formPom.getTableData("Hobbies");
        Assert.assertEquals(actualHobbies, HOBBY, "Hobbies nu coincide!");
        String actualStateCity = formPom.getTableData("State and City");
        Assert.assertEquals(actualStateCity, STATE + " " + CITY, "State and City nu coincide!");



        System.out.println("Finish test");
    }

    @AfterMethod
    public void AfterMethod(){
        driver.quit();
    }
}
