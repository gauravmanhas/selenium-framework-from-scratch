package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.WebDriver;

public class CategoryFilterPage extends BasePage {

    public WebDriver driver;
    private String CATEGORY_FILTER = "xpath=>//Select[@name = 'categories']";

    public CategoryFilterPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public ResultsPage select(String option){
        selectOption(CATEGORY_FILTER,option,"Category Filter");
        return new ResultsPage(driver);
    }
}
