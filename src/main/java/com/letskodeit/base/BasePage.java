package com.letskodeit.base;

import org.openqa.selenium.WebDriver;

public class BasePage extends CustomDriver{
    WebDriver driver;

    public BasePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean verifyPageTitle(String expTitle){
        return driver.getTitle().equalsIgnoreCase(expTitle);
    }

    public boolean verifyCurrentUrl(String expURL){
        return driver.getCurrentUrl().equalsIgnoreCase(expURL);
    }
}
