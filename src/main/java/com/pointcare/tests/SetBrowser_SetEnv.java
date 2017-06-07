package com.pointcare.tests;

import com.pointcare.common.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import static jxl.biff.FormatRecord.logger;

/**
 * Created by mandeep.
 */
public class SetBrowser_SetEnv {
    @Test
    public void select_FirefoxBrowser(){
        WebDriver driver = new FirefoxDriver();
        Properties properties = new Properties();
        driver.get(properties.defaultEnvironmentName());
        logger.info("Env"+ properties.defaultEnvironmentName());
    }
    @Test
    public void select_ChromeBrowser(){
        WebDriver driver = new ChromeDriver();
        Properties properties = new Properties();
        driver.get(properties.defaultEnvironmentName());
        logger.info("Env"+ properties.defaultEnvironmentName());    }
}
