package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import com.letskodeit.base.CustomDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MyCoursesPage extends BasePage {
    public WebDriver driver;
    private JavascriptExecutor js;
    //private String PAGE_HEADING = "xpath=>//h1[@class='dynamic-heading']";

    public MyCoursesPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /*public String verifyPageHeading(){
        return getText(PAGE_HEADING, "Page Heading");
    }*/
}
