package com.letskodeit.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

public class WebDriverFactory {
    // Singleton
    // Only one instance of the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
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
        setDriverPath(browser);

        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("firefox")||browser.equalsIgnoreCase("ff")) {
                    FirefoxOptions ffOptions = setFFOptions();
                    driver = new FirefoxDriver(ffOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions chromeOptions = setChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("internet explorer")||browser.equalsIgnoreCase("ie")) {
                    InternetExplorerOptions ieOptions = setIEOptions();
                    driver = new InternetExplorerDriver(ieOptions);
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

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    private void setDriverPath(String browser) {
        String driverPath = "";
        //the below line will give the first 3 letters of the OS you are using like win or mac
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        System.out.println("OS Name is: "+os);
        String directory = System.getProperty("user.dir") + "/drivers";
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("ff")) {
            driverKey = "webdriver.gecko.driver";
            driverValue = "geckoDriver";
        } else if (browser.equalsIgnoreCase("chrome")) {
            driverKey = "webdriver.chrome.driver";
            driverValue = "chromeDriver";
        } else if (browser.equalsIgnoreCase("internet explorer") || browser.equalsIgnoreCase("ie")) {
            driverKey = "webdriver.ie.driver";
            driverValue = "IEDriverServer";
        } else {
            System.out.println("Browser type not supported, hence launching chrome");
            driverKey = "webdriver.chrome.driver";
            driverValue = "chromeDriver";
        }

        driverPath = directory + "/" +driverValue + (os.equalsIgnoreCase("win") ? ".exe" : "");
        System.out.println(driverPath);

        System.setProperty(driverKey, driverPath);
    }
    /***
     * Set Chrome Options
     * @return options
     */
    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); old and depracated, no longer in use
        //the below code is used to disable / enable multiple things.
        //excludeSwitches is basically a key that takes a list so in out list below:
        //Arrays.asList("disable-popup-blocking", "enable-automation")
        //here we have added 2 arguments/values:
        //-disable-pop-up-blocking (to block pop-up) AND
        //"enable-automation" => to remove chrome infobar ("chrome is being controlled....")
        //options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption
        ("excludeSwitches", Arrays.asList("disable-popup-blocking", "enable-automation"));

        return options;
    }

    /***
     * Set Firefox Options
     * @return options
     */
    private FirefoxOptions setFFOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }

    /***
     * Set Internet Explorer Options
     * @return options
     */
    private InternetExplorerOptions setIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.
                INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return options;
    }
}