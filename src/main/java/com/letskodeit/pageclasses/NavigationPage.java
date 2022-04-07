package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.time.Duration;
import java.util.List;

public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public WebDriver driver;
    JavascriptExecutor js;

    private String SIGNIN_Link = "xpath=>//a[@href='/login']";
    private String HOME_LINK = "xpath=>//a[@href='/' and text()='HOME']";
    private String ALL_COURSES_LINK = "xpath=>//a[@href='/courses' and text()='ALL COURSES']";
    private String SUPPORT_LINK = "xpath=>//a[@href='/support' and text()='SUPPORT']";
    private String MY_COURSES_LINK= "xpath=>//a[@href='/mycourses' and text()='MY COURSES']";
    private String USER_ICON = "xpath=>//img[@class='zl-navbar-rhs-img ']";
    private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
    private String NAVIGATION_LINKS = "xpath=>//li[@data-action='link']";

    public LoginPage openSignInPage(){
        //driver.findElement(By.xpath(SIGNIN_Link)).click();
        elementClick(SIGNIN_Link, "SIGN IN Link");
        return new LoginPage(driver);
    }

    public HomePage homePage(){
        //driver.findElement(By.xpath(HOME_LINK)).click();
        elementClick(HOME_LINK, "Home Link");
        return new HomePage();
    }

    public AllCoursesPage allCoursesPage(){
        //driver.findElement(By.xpath(ALL_COURSES_LINK)).click();
        elementClick(ALL_COURSES_LINK,"All Courses Link");
        return new AllCoursesPage(driver);
    }

    public SupportPage supportPage(){
        //driver.findElement(By.xpath(SUPPORT_LINK)).click();
        elementClick(SUPPORT_LINK, "Support Link");
        return new SupportPage(driver);
    }

    public MyCoursesPage myCoursesPage(){
        elementClick(MY_COURSES_LINK, "My Courses Link");
        return new MyCoursesPage(driver);
    }

    public int navigationBarLinks(){
        List<WebElement> list = getElementList(NAVIGATION_LINKS, "Links under navigation bar");
        return list.size();
    }

    public boolean isUserLoggedIn(){
        return isElementPresent(USER_ICON,"User Icon");
    }

    public void logout(){
        elementClick(USER_ICON,"User Icon", 2);
        elementClick(LOGOUT_LINK,"Logout Link");
        //driver.findElement(By.xpath(USER_ICON)).click();
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        //WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable((By.xpath(LOGOUT_LINK))));
        //js.executeScript("arguments[0].click", logoutLink);
        //logoutLink.click();
    }

}
