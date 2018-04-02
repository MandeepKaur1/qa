package regression.com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import static jxl.biff.FormatRecord.logger;


/**
 * Created by mandeep.
 */

public class StartUITest {

    public static WebDriver driver = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    String sessionName = getClass().getName();

    /*Set Browser to run scripts locally or on browserStack*/
    //public static String browserName; //To test run on BrowserStack
    public static String browserName = "Chrome"; //To test run locally

    /**
     * Getting Browser to run all scripts
     *
     * @param BrowserType = Browser
     */
    public static WebDriver getBrowser(String BrowserType) {
        logger.info("All Browser Instance");
        {
            if (BrowserType.equals("Firefox")) {
                logger.info("Open FireFox Browser");
                driver = new FirefoxDriver();
            } else if (BrowserType.equals("Chrome")) {
                logger.info("Open Chrome Browser");
                driver = new ChromeDriver();
            } else if (BrowserType.equals("IE")) {
                logger.info("Open Internet Explore Browser");
                driver = new InternetExplorerDriver();
            } else if (BrowserType.equals("Safari")) {
                logger.info("Open Safari Browser");
                driver = new SafariDriver();
            }
        }
        return driver;
    }

    /**
     * getBrowser - Browser to run test against.
     * getEnvironment - getting test env
     */
    @BeforeClass
    public void getDriver() {
        getBrowser(browserName);
        driver.manage().window().maximize();
        getEnvironment();
    }

    /**
     * Getting Testing Environment.
     */
    public void getEnvironment() {
        logger.info("Env Is set to ");
        driver.get("Test Env");
    }

    /**
     * Close WebDriver after every class.
     */
    @AfterClass
    public void closeDriver() {
        if (driver != null)
            driver.getCurrentUrl();
        driver.quit();
    }

    //Run Parallel Test on Browser Stack
    //@BeforeClass
    //@Parameters(value = {"platform", "browserName","version", "OSversion"})
    public void run_test_on_browserStack(String platform, String browserName, String version, String OSversion) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platform", platform);
        capability.setCapability("browserName", browserName);
        capability.setCapability("version", version);
        capability.setCapability("OSversion", OSversion);

        driver = new RemoteWebDriver(
                new URL("BrowserStack URL"),
                capability);
        driver.manage().window().maximize();
        getEnvironment();
    }

    /*
    * Run tests on Multiple devices on BrowserStack
    */

    // @BeforeClass
    //Connection to Browser Stack
    public void remote_browser_chrome() throws MalformedURLException {
        logger.info("Selected Browser to Run Test is Chrome");
        browserName = "Chrome";
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("name", sessionName);

        driver = new RemoteWebDriver(
                new URL("BrowserStack URL"), capabilities
        );
        driver.manage().window().maximize();
        getEnvironment();
    }


}