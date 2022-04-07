package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.pageclasses.CategoryFilterPage;
import com.letskodeit.pageclasses.NavigationPage;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @BeforeClass
    public void setUp() throws InterruptedException {
        navigateTo = loginPage.signInWith("autotestweb15@gmail.com","Lkipwd@135531");
    }

    @Test(groups = {"Sanity", "Regression"})
    public void verifySearchCourse() throws InterruptedException {
        allCoursesPage = navigateTo.allCoursesPage();
        String courseName = "Rest API";
        results = allCoursesPage.courseSearch(courseName);
        Thread.sleep(1000);
        boolean searchResult = results.verifySearchResult(courseName);
        System.out.println(searchResult);
        Assert.assertTrue(searchResult);
    }

    @Test(groups = {"Sanity", "Regression"})
    public void filterByCategory() throws InterruptedException {
        navigateTo = new NavigationPage(driver);
        navigateTo.allCoursesPage();
        category = new CategoryFilterPage(driver);
        String option = "Selenium WebDriver";
        results = category.select(option);
        Thread.sleep(1000);
        boolean filterResult = results.verifySearchResult(option);
        System.out.println(filterResult);
        Assert.assertTrue(filterResult);
    }

}
