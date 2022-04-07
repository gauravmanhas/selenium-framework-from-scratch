package com.letskodeit.base;

import com.letskodeit.pageclasses.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected String baseURL;
    protected LoginPage loginPage;
    protected NavigationPage navigateTo;
    protected AllCoursesPage allCoursesPage;
    protected ResultsPage results;
    protected CategoryFilterPage category;


    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        baseURL = "https://courses.letskodeit.com/";
        driver.get(baseURL);
        navigateTo = new NavigationPage(driver);
        loginPage = navigateTo.openSignInPage();
    }
    /*@BeforeMethod
    public void clearCheckPoint(){
        CheckPoint.clearHashMap();
    }*/

    @AfterClass
    public void commonTearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }
}