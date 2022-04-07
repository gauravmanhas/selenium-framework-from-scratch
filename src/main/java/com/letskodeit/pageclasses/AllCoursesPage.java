package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.WebDriver;

public class AllCoursesPage extends BasePage {

    public AllCoursesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    private String EXP_URL = "https://courses.letskodeit.com/courses";
    private String CATEGORYFILTER = "xpath=>//Select[@name='categories']";
    private String SEARCH_COURSE_FIELD = "xpath=>//input[@id='search']";
    private String SEARCH_COURSE_BUTTON = "xpath=>//button[@type='submit']";

    public boolean isOpen() {
        return verifyCurrentURL(EXP_URL);
    }

    public void filterWithCategory() {
        selectOption(CATEGORYFILTER, "API Automation", "Category Filter");
    }

    public ResultsPage courseSearch(String courseName) {
        sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field");
        elementClick(SEARCH_COURSE_BUTTON, "Search Icon");
        return new ResultsPage(driver);

    }

}
