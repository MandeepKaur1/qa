package com.pointcare.tests;


import com.pointcare.common.Browser;
import com.pointcare.common.FrameworkConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static jxl.biff.FormatRecord.logger;


/**
 * The BaseUITest class is the main class for all UI test scripts.
 */
public class BaseUITest {

    private final Browser browser = new Browser();
    WebDriver driver;

    /**
     * Method for replacing text input in web element.
     *
     * @param by    By selector for text input element
     * @param value text WebElement found on page by given selector

    protected void setText(By by, String value) {
        WebElement element = driver.findElement(by);
        System.out.println("setting text to " + value);
        element.sendKeys(value);
    }
*/
    public void setText1(By by, String value) {
        WebElement element = driver.findElement(by);
        System.out.println("setting text to " + value);
        element.sendKeys(value);
    }
    protected void click(By by) {

        WebElement element = driver.findElement(by);
        logger.info("element " + element);
        element.click();
    }

    public void setText(By by, String value) {
        WebElement element = getWebElement(by);
        element.findElement(by);
        logger.debug("setting text to " + value);
        element.sendKeys(value);
    }

    /**

     * Method for search of web element

     *

     * @param by By class describing element location

     * @return WebElement found on page by given selector

     */

    protected WebElement getWebElement(By by) {
        return browser.getWebElement(by);
    }

}
