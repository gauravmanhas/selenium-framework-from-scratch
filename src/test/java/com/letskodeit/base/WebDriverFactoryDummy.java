package com.letskodeit.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class WebDriverFactoryDummy {
    // Singleton
    // Only one instance of the class can exist at a time
    private static final WebDriverFactoryDummy instance = new WebDriverFactoryDummy();

    private WebDriverFactoryDummy() {
    }

    public static WebDriverFactoryDummy getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

    /***
     * Get driver instance based on the browser type
     * @param browser
     * @return
     */
    public WebDriver getDriver(String browser) {
        WebDriver driver = null;

        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                } else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("ff")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                } else if (browser.equalsIgnoreCase("internet explorer") || browser.equalsIgnoreCase("ie")) {
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    threadedDriver.set(driver);
                }else{
                    System.out.println("Incorrect browser input, hence launching <<chrome>>");
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            threadedDriver.get().manage().window().maximize();
        }

        return threadedDriver.get();
    }
    public void quitDriver(){
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

}

