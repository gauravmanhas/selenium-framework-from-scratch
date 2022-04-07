package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests extends BaseTest {

    /*@BeforeClass
    public void setUp(){

    }*/

    @Test(groups = {"Sanity", "Regression"}, priority = 1)
    public void testLogin() throws InterruptedException{
        SoftAssert Assert = new SoftAssert();
        navigateTo = loginPage.signInWith("autotestweb15@gmail.com","Lkipwd@135531");
        boolean result = navigateTo.isUserLoggedIn();
        int size = navigateTo.navigationBarLinks();
        System.out.println("Links available under navigation bar: "+size);
        Assert.assertEquals(size,4,"Assertion Failed Expected value is 3 and Actual is 4");
        System.out.println("Assertion 1 - checked links count under navigation bar");
        Assert.assertTrue(result,"Expected true but found false");
        System.out.println("Assertion 2 - checked the userImage icon");
        System.out.println("Assert ALL = Before");
        Assert.assertAll();
        System.out.println("Assert ALL = After");
    }

    /*@Test(groups = {"Sanity", "Regression"}, priority = 1)
    public void testLogin() throws InterruptedException{
        SoftAssert Assert = new SoftAssert();
        navigateTo = loginPage.signInWith("autotestweb15@gmail.com","Lkipwd@135531");
        boolean result = navigateTo.isUserLoggedIn();
        CheckPoint.mark("testLogin-Assertion-1",
        false,"User Login verification using Image Icon");

        int size = navigateTo.navigationBarLinks();
        CheckPoint.markFinal("testLogin-Assertion-2",
        (size==3?true:false),"verification of Link count under navigation bar after login");
        /////enable the @beforemethod as well from BaseTest class
    }*/

    @Test(groups = "Regression" , priority = 0)
    public void testInvalidLogin() throws InterruptedException {
        navigateTo = loginPage.signInWith("autotestweb15@gmail.com","1234");
        loginPage.getErrorMessage();
        boolean result = navigateTo.isUserLoggedIn();
        Assert.assertFalse(result);
    }

    @AfterMethod(groups = {"Sanity", "Regression"})
    public void afterLogin(){
        if(navigateTo.isUserLoggedIn()){
            navigateTo.logout();
            navigateTo.openSignInPage();
        }
    }

}