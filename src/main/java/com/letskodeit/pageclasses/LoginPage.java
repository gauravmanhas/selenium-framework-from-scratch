package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebDriver driver;
    private String EMAIL_FIELD = "xpath=>//input[@name='email' and @placeholder='Email Address']";
    private String PASSWORD_FIELD = "xpath=>//input[@id='password']";
    private String LOGIN_BUTTON = "xpath=>//input[@type='submit']";
    private String ERROR_MESSAGE = "xpath=>//span[@class='dynamic-text help-block']";

    public NavigationPage signInWith(String email, String password) throws InterruptedException {
        sendData(EMAIL_FIELD, email, "Email Field");
        sendData(PASSWORD_FIELD, password,"Password Field");
        Thread.sleep(1000);
        elementClick(LOGIN_BUTTON,"Login Button");
        return new NavigationPage(driver);
    }

    public void getErrorMessage() {
        System.out.println(getText(ERROR_MESSAGE, "Error Message"));
    }

}
