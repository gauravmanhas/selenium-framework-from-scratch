package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import com.letskodeit.utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage extends BasePage {
    public WebDriver driver;
    private String AVAILABLE_COURSES = "xpath=>//div[@class='zen-course-list']";
    public String SEARCH_RESULT = "xpath=>//h4[@class='dynamic-heading' and contains(text(),'%s')]";

    public ResultsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean checkAllCourses(){
        return isElementPresent(AVAILABLE_COURSES,"Available Courses");
        /*boolean flag = false;
        List<WebElement> allAvailableCourses= driver.findElements(By.xpath(AVAILABLE_COURSES));
        if(allAvailableCourses.size() > 0){
            System.out.println(allAvailableCourses.size()+ " Courses available in total.");
            flag = true;

        }else{
            System.out.println("0 courses available");
            flag = false;
        }
        return flag;*/
    }

    public boolean verifySearchResult(String searchTerm) {
        return verifySearchedResultsBasedOnSearchTerm(SEARCH_RESULT, searchTerm);
    }

}
