package org.example.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(FormTest.class);


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
        logger.info("Start before method");
//        driver = Driver.getAutoLocalDriver();
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest(){
        logger.info("Start form test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.pause(3000);
        formPom.clickForms();
        formPom.clickPracticeForm();
        formPom.setFirstName(FIRST_NAME);
        logger.info("Set first name");
        formPom.setLastName(LAST_NAME);
        logger.info("Set last name");
        formPom.setUserEmail(EMAIL);
        logger.info("Set email");
        formPom.setGender(GENDER);
        logger.info("Set gender");
        formPom.setNumber(NUMBER);
        logger.info("Set number");
        formPom.setDate(DATE);
        logger.info("Set date");
        formPom.setSubject(SUBJECT);
        logger.info("Set subject");
        formPom.setHobbies(HOBBY);
        logger.info("Set hobbies");
        formPom.setState(STATE);
        logger.info("Set state");
        formPom.setCity(CITY);
        logger.info("Set city");
        formPom.pause(3000);
        logger.info("End form test");
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
        logger.info("Start after method");
        driver.quit();
    }
}
