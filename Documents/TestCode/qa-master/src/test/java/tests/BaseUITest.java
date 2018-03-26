package tests;


import com.sun.glass.events.KeyEvent;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static jxl.biff.FormatRecord.logger;


/**
 * The BaseUITest class is the main class for all UI test scripts.
 */
public class BaseUITest {

    public static WebDriver driver;

    private final static int TIME_FOR_WAITING_SECONDS = 60;

    /**
     * Go to other URL
     *
     * @param url URL to go to
     */
    protected void navigateTo(String url) {
        logger.info("navigation to url: " + url);
        driver.get(url);
    }

    /**
     * Method for setting the text.
     *
     * @param by    By selector for text input element
     * @param value text WebElement found on page by given selector'
     */
    protected void setText(By by, String value) {
        WebElement element = driver.findElement(by);
        System.out.println("setting text to " + value);
        element.sendKeys(value);
    }

    /**
     * Method for setting the text.
     *
     * @param by    By selector for text input element
     * @param value text WebElement found on page by given selector'
     */
    protected void setTextByAction(By by, String value) {
        WebElement element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        System.out.println("setting text to " + value);
        action.sendKeys(value);
        action.build().perform();
    }

    /**
     * Method for click on element .
     *
     * @param by By selector for text input element
     */
    protected void clickByAction(By by) {
        WebElement element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        action.click();
        action.build().perform();
    }

    /**
     * Method for clearing the text.
     *
     * @param by By selector for text input element
     */
    protected void clearText(By by) {
        WebElement element = driver.findElement(by);
        element.clear();
    }

    /**
     * Method for clicking on element
     *
     * @param by By selector for clickable element
     */
    protected void click(By by) {
        WebElement element = driver.findElement(by);
        logger.info("element " + element);
        element.click();
    }

    /**
     * Go to Current URL and refresh tab
     *
     * @param url URL to go to
     */
    protected void refreshPage(String url) {
        logger.info("navigation to url: " + url);
        driver.navigate().refresh();
    }

    /**
     * Switch to tab
     *
     * @param value number of tab
     */
    public void switchTab(int value) {
        logger.debug("Switching tab to, num: " + value);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(value));
    }

    /**
     * Method for getting input on text web element
     *
     * @param selector By selector for element
     * @return text found in WebElement
     */
    protected String getText(By selector) {
        return getText(getWebElement(selector));
    }

    /**
     * Method for getting input on text web element
     *
     * @param element element for getting test
     * @return text found in WebElement
     */
    protected String getText(WebElement element) {
        try {
            String text = element.getText();
            logger.debug("Read text: " + text);
            return text;
        } catch (Exception e) {
            logger.error("Cannot get text");
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for search of web element
     *
     * @param by By class describing element location
     * @return WebElement found on page by given selector
     */
    protected WebElement getWebElement(By by) {
        WebElement element = driver.findElement(by);
        logger.debug("Get web element, text: " + element.getText());
        return element;
    }

    /**
     * Method for selecting item from drop down select by visible text
     *
     * @param by   By selector for select element
     * @param item name of item
     */
    protected void setSelect(By by, String item) {
        WebElement select = driver.findElement(by);
        Select dropDown = new Select(select);
        dropDown.selectByVisibleText(item);
        logger.debug("selected item: " + item);
    }

    /**
     * Method which verifies presence of some text on web page
     *
     * @param text text to check
     * @return true if page contains necessary text
     */
    protected boolean isPageContainsText(String text) {
        return driver.getPageSource().contains(text);
    }

    /**
     * Get url of current tab
     *
     * @return url of current tab
     */
    protected String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("Current url: " + url);
        return url;
    }

    /**
     * Method which verifies presence of some text on web page
     *
     * @return page Source Code
     */
    protected String getPageSourceCode() {
        String source = driver.getPageSource().toString();
        logger.debug("Page source: " + source);
        return source;
    }

    /**
     * Performs scrolling to the given web element using JavaScript.
     *
     * @param element the element to scroll to
     */
    public void scrollToElement(WebElement element) {
        logger.debug("Scrolling to web element");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs scrolling to the given element using JavaScript.
     *
     * @param by the locator of the element to scroll to
     */
    public void scrollToElement(By by) {
        scrollToElement(getWebElement(by));
    }

    /**
     * Performs hovering on given element.
     *
     * @param by selector of element
     */
    protected void hoverOnElement(By by) {
        logger.debug("Hover on element");
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(by)).build().perform();
    }

    /**
     * Returns visible text of currently selected option from the dropdown.
     *
     * @param by By selector for select element
     * @return currently selected option in the select
     */
    protected String getSelectedOptionInDropdown(By by) {
        WebElement select = driver.findElement(by);
        Select dropDown = new Select(select);
        return dropDown.getFirstSelectedOption().getText();
    }

    /**
     * click on first visible element(If page contains more than 1 element with same property).
     *
     * @param by By selector for select element
     */
    protected void clickOnFirstElement(By by) {
        int numberOfTimesElementPresent = driver.findElements(by).size();
        logger.info("numberOfTimesElementPresent is " + numberOfTimesElementPresent);
        driver.findElements(by).get(numberOfTimesElementPresent - 1).click();
    }

    /**
     * select first visible element(If page contains more than 1 element with same property).
     *
     * @param by By selector for select element
     */
    protected void setSelectOnFirstElement(By by, String item) {
        int numberOfTimesElementPresent = driver.findElements(by).size();
        logger.info("numberOfTimesElementPresent is " + numberOfTimesElementPresent);
        WebElement select = driver.findElements(by).get(numberOfTimesElementPresent - 1);
        Select dropDown = new Select(select);
        dropDown.selectByVisibleText(item);
        logger.debug("selected item: " + item);
    }

    /**
     * setText on first visible element(If page contains more than 1 element with same property).
     *
     * @param by By selector for select element
     */
    protected void setTextOnFirstElement(By by, String value) {
        int numberOfTimesElementPresent = driver.findElements(by).size();
        logger.info("numberOfTimesElementPresent is " + numberOfTimesElementPresent);
        WebElement element = driver.findElements(by).get(numberOfTimesElementPresent - 1);
        System.out.println("setting text to " + value);
        element.sendKeys(value);
    }

    /**
     * Finds all elements within the current context using the given mechanism.
     *
     * @param by locating mechanism to use
     * @return a list of all WebElements, or an empty list if nothing matches
     */
    protected List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    /**
     * checks if element is present on the page. Wait for a 3 seconds and returns false if not found
     *
     * @param by selector for desired element
     * @return is element present on the page
     */
    protected boolean isElementPresent1(By by) throws InterruptedException {
        List<WebElement> element = getWebElements(by);
        wait(5);
        boolean isPresent;
        if (element == null || element.isEmpty()) {
            logger.debug("WebElement doesn't exists: " + by);
            isPresent = false;
        } else {
            logger.debug("WebElement exists: " + by);
            isPresent = true;
        }
        wait(5);
        return isPresent;
    }

    /**
     * Waits while an element will be visible and enabled such that you can click it.
     *
     * @param by selector of web element
     */
    protected void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits while an element will be visible and enabled such that you can click it.
     *
     * @param by selector of web element
     */
    public void waitForElementClickable(By by) {
        final int waitForElementTimeout = 120;
        int MILLS_IN_SEC = 1000;
        long started = System.currentTimeMillis();
        new WebDriverWait(driver, 30);
        long time = (System.currentTimeMillis() - started) / MILLS_IN_SEC;
        logger.debug("Waited for element: " + time);
    }

    /**
     * Method for search of web element inside another web element
     *
     * @param by By class describing element location
     * @return true if WebElement was found on page by given selector, false otherwise
     */
    public boolean isElementExists(By by) {
        try {
            WebElement element = getWebElement(by);
            logger.debug("Get web element, tagname: " + element.getTagName());
            logger.debug("Get web element, text: " + element.getText());
            return true;
        } catch (Exception e) {
            logger.debug("WebElement isn't exists: " + by);
            return false;
        }
    }

    /**
     * Specifies the amount of time the driver should wait when searching for an element
     * if it is not immediately present.
     *
     * @param sec the amount of time to wait
     */
    public static void setImplicitWait(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    /**
     * Specifies the default amount of time the driver should wait when searching for an element
     * if it is not immediately present.
     */
    public static void setDefaultImplicitWait() {
        setImplicitWait(TIME_FOR_WAITING_SECONDS);
    }

    /**
     * checks if element is present on the page. Wait for a 3 seconds and returns false if not found
     *
     * @param by selector for desired element
     * @return is element present on the page
     */
    public boolean isElementPresent(By by) {
        setImplicitWait(3);
        List<WebElement> element = getWebElements(by);
        boolean isPresent;
        if (element == null || element.isEmpty()) {
            logger.debug("WebElement doesn't exists: " + by);
            isPresent = false;
        } else {
            logger.debug("WebElement exists: " + by);
            isPresent = true;
        }
        setDefaultImplicitWait();

        return isPresent;
    }

    /**
     * Switches to a popup window.
     */
    public void switchToPopupWindow() {
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
    }
}
