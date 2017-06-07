package com.pointcare.common;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import static jxl.biff.FormatRecord.logger;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.*;
import java.util.List;

/**
 * The Browser class represents an idealised web browser.
 */
public class Browser {

    private WebDriver driver;
    private ArrayList<String> tabs;

    protected Logger logger = Logger.getLogger(getClass());

    /**
     * Go to other URL in the new Tab
     *
     * @param url URL to go to
     * @throws AWTException Abstract Window Toolkit exception has occurred
     */
    public void navigateToNewTab(String url) throws AWTException {
        logger.info("switch tab with new url: " + url);
        openNewTab();
        driver.navigate().to(url);
    }

    /**
     * Method for replacing text input in web element.
     *
     * @param element WebElement element to set text into
     * @param value   text WebElement found on page by given selector
     */
    public void setText(WebElement element, String value) {
        try {
            logger.debug("clearing text");
            element.clear();
            logger.debug("setting text to " + value);
            element.sendKeys(value);
            Assert.assertEquals(element.getAttribute("value"), value);
        } catch (Exception e) {
            logger.error("Cannot set text value:");
            logger.error("text:" + value);
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens a new tab with focus on it using java.awt.Robot functionality.
     *
     * @throws AWTException Abstract Window Toolkit exception has occurred
     */
    public void openNewTab() throws AWTException {
        logger.info("Open new tab and set it active");
        Robot r = new Robot();
        if (SystemUtils.IS_OS_MAC_OSX) {
            logger.info("CMD+T");
            r.keyPress(KeyEvent.VK_META);
            r.keyPress(KeyEvent.VK_T);
            r.keyRelease(KeyEvent.VK_T);
            r.keyRelease(KeyEvent.VK_META);
        } else {
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_T);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_T);
        }
        switchTab(1);
    }

    /**
     * Switch to tab
     *
     * @param value number of tab
     */
    public void switchTab(int value) {
        refreshTabList();
        logger.debug("Switching tab to, num: " + value);
        driver.switchTo().window(tabs.get(value));
    }

    /**
     * Close currently active tab
     */
    @SuppressWarnings("unused")
    public void closeTab() {
        logger.debug("Closing tab");
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
    }

    public void refreshTabList() {
        logger.debug("Refreshing tab list");
        tabs = new ArrayList<String>(driver.getWindowHandles());
    }

    /**
     * Returns number of open tabs.
     *
     * @return the number of open tabs.
     */
    public int getNumberOfOpenTabs() {
        refreshTabList();
        return (tabs != null) ? tabs.size() : 0;
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

    /**
     * Switches to another frame using its located WebElement.
     *
     * @param by selector to locate the frame
     */
    public void switchToFrame(By by) {
        WebElement element = driver.findElement(by);
        driver.switchTo().frame(element);
    }

    /**
     * Go to other URL
     *
     * @param url URL to go to
     */
    public void navigateTo(String url) {
        logger.info("navigation to url: " + url);
        driver.get(url);
    }

    /**
     * Method for getting input on text web element
     *
     * @param selector By selector for element
     * @return text found in WebElement
     * @throws NoSuchElementException if element was not found
     */
    public String getText(By selector) {
        return getText(getWebElement(selector));
    }

    /**
     * Method for getting input on text web element
     *
     * @param element element for getting test
     * @return text found in WebElement
     * @throws NoSuchElementException if element was not found
     */
    public String getText(WebElement element) {
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
     * Method for selecting element from dynamically generated list.
     * Working only once on one unique element on the page between page refreshes
     *
     * @param selector     selector for clicking to get items list
     * @param elementIndex number of element for selecting starting from 1 from top
     * @throws NoSuchElementException if element was not found
     */
    public void setSelectInput(By selector, int elementIndex) {
        WebElement element = getWebElement(selector);
        Assert.assertTrue(element.isDisplayed());
        setSelectInput(element, elementIndex);
    }

    /**
     * Method for selecting element from dynamically generated list.
     * Working only once on one unique element on the page between page refreshes
     *
     * @param element      WebElement element for clicking to get items list
     * @param elementIndex number of element for selecting starting from 1 from top
     * @throws NoSuchElementException if element was not found
     */
    public void setSelectInput(WebElement element, int elementIndex) {
        logger.debug("selecting SelectInput element by item index");
        click(element);
        //todo: create method for select
        By listSelector = By.cssSelector(String.format("ul>li:nth-child(%d)>div.x-combo-list-item", elementIndex));
        List<WebElement> elementsList = getElementsList(listSelector);
        click(elementsList.get(elementsList.size() - 1));
    }


    /**
     * Method for replacing value from input in web element.
     *
     * @param selector By selector for text input element
     * @return String value
     */
    @SuppressWarnings("unused")
    public String getTextValue(By selector) {
        return getTextValue(getWebElement(selector));
    }

    /**
     * Method for replacing value from input in web element.
     *
     * @param element WebElement input element
     * @return String value
     */
    public String getTextValue(WebElement element) {
        try {
            String text = element.getAttribute("value");
            logger.debug("Read text: " + text);
            return text;
        } catch (Exception e) {
            logger.error("Cannot get text");
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get url of current tab
     *
     * @return url of current tab

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("Current url: " + url);
        return url;
    }

    /**
     * Captures all the dropdown items and store them in an array
     *
     * @param by By selector for select element
     */
    public void getDropDownList(By by) {
        WebElement select = getWebElement(by);
        Assert.assertTrue(select.isDisplayed());
        List<WebElement> dropDownList = select.findElements(By.cssSelector("#type > option"));
        String items[] = new String[dropDownList.size()];
        for (int i = 0; i < dropDownList.size(); i++) {
            items[i] = dropDownList.get(i).getText();
        }
    }

    /**
     * Method for selecting item from drop down select by visible text
     *
     * @param by   By selector for select element
     * @param item name of item
     * @throws NoSuchElementException if element was not found
     */
    public void setSelect(By by, String item) {
        if (!item.equals(FrameworkConstants.DEFAULT)) {
            try {
                WebElement select = getWebElement(by);
                Assert.assertTrue(select.isDisplayed());
                Select dropDown = new Select(select);
                dropDown.selectByVisibleText(item);
                logger.debug("selected item: " + item);
            } catch (Exception e) {
                logger.error("Cannot select item: " + item);
                logger.debug(e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Returns visible text of currently selected option in the select.
     *
     * @param by By selector for select element
     * @return currently selected option in the select
     */
    public String getSelectedOptionInDropdown(By by) {
        WebElement select = driver.findElement(by);
        Select dropDown = new Select(select);
        return dropDown.getFirstSelectedOption().getText();
    }

    /**
     * Method for checking on web element
     *
     * @param by        By selector for checkbox
     * @param isChecked check or uncheck element
     * @throws NoSuchElementException if element was not found
     */
    public void setCheck(By by, boolean isChecked) {
        setCheck(getWebElement(by), isChecked);
    }

    /**
     * Method for checking on web element
     *
     * @param element   WebElement checkbox
     * @param isChecked check or uncheck element
     * @throws NoSuchElementException if element was not found
     */
    public void setCheck(WebElement element, boolean isChecked) {
        try {
            if (element.isSelected() != isChecked) element.click();
            logger.debug("Set check: " + isChecked);
        } catch (Exception e) {
            logger.error("Cannot read check box");
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for getting checkbox status
     *
     * @param by By selector for checkbox
     * @return boolean - is element checked
     * @throws NoSuchElementException if element was not found
     */
    @SuppressWarnings("unused")
    public boolean isChecked(By by) {
        return isChecked(getWebElement(by));
    }

    /**
     * Method for getting checkbox status
     *
     * @param element WebElement checkbox
     * @return boolean - is element checked
     * @throws NoSuchElementException if element was not found
     */
    public boolean isChecked(WebElement element) {
        try {
            if (element.isSelected()) {
                logger.debug("Chebox is selected");
                return true;
            } else {
                logger.debug("Chebox is not selected");
                return false;
            }

        } catch (Exception e) {
            logger.error("Cannot read check box");
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for search of web element
     *
     * @param by By class describing element location
     * @return WebElement found on page by given selector
     * @throws NoSuchElementException if element was not found

    public WebElement getWebElement(By by) {
            WebElement element = driver.findElement(by);
            logger.debug("Get web element, text: " + element.getText());
            return element;
    }*/

    public WebElement getWebElement(By by) {
            WebElement element = driver.findElement(by);
            logger.debug("Get web element, text: " + element.getText());
            return element;

    }

    /**
     * Method for search of web element inside another web element
     *
     * @param source WebElement element to search into
     * @param by     By class describing element location
     * @return WebElement found on page by given selector
     * @throws NoSuchElementException if element was not found
     */
    public WebElement getWebElement(WebElement source, By by) {
        try {
            WebElement element = source.findElement(by);
            logger.debug("Get web element, tagName: " + element.getTagName());
            logger.debug("Get web element, text: " + element.getText());
            return element;
        } catch (Exception e) {
            logger.error("WebElement not found: " + by);logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for search for web elements inside another web element
     *
     * @param source WebElement element to search into
     * @param by     By class describing elements selector
     * @return list of WebElements found in element by given selector
     * @throws NoSuchElementException if no elements was found
     */
    public List<WebElement> getWebElements(WebElement source, By by) {
        try {
            List<WebElement> list = source.findElements(by);
            if (list.size() == 0)
                throw new RuntimeException("No elements are found with given selector in given WebElement");
            logger.debug("Get web elements, total number: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("WebElements not found: " + by);
            logger.debug(e);
            throw new RuntimeException(e);
        }
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
     * Method for search of web element inside another web element
     *
     * @param source WebElement element to search into
     * @param by     By class describing element location
     * @return WebElement found on page by given selector
     * @throws NoSuchElementException if element was not found
     */
    public List<WebElement> getElementsList(WebElement source, By by) {
        try {
            List<WebElement> list = source.findElements(by);
            logger.debug("Get " + list.size() + " elements");
            return list;
        } catch (Exception e) {
            logger.error("WebElements not found: " + by);logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for clicking on item in drop down menu
     *
     * @param path By elements for path to item. Items will be hovered on in given order and then
     *             clicking will be performed on the last path element
     */
    @Deprecated
    public void clickFromDropDownMenu(By... path) {
        logger.debug("Clicking from drop down menu");
        logger.debug("Path size: " + path.length + " elements");
        if (path.length == 0) {
            return;
        }
        Actions action = new Actions(driver);

        for (By by : path) {
            action.moveToElement(getWebElement(by)); //.click();
        }
        action.build().perform();
        click(path[path.length - 1]);
    }

    /**
     * Method for getting list of elements
     *
     * @param by By class describing elements location
     * @return List of WebElement's found on page by given selector
     * @throws NoSuchElementException if no elements was found
     */
    public List<WebElement> getElementsList(By by) {
        List<WebElement> list = driver.findElements(by);
        logger.debug("Found elements: " + list.size());
        if (list.size() == 0) {
            logger.error("WebElement not found: " + by);
            throw new NoSuchElementException("Element not Found " + by);
        }
        return list;
    }

    /**
     * Close all windows of browser
     */
    public void quit() {
        if (driver != null)
            driver.quit();
    }

    /**
     * Close web browser's current window
     */
    public void close() {
        logger.debug("Close");
        if (driver != null)
            driver.close();
    }

    /**
     * Deletes all the cookies from the current domain.
     */
    public void deleteAllCookies() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    /**
     * The method helps us to run JS scripts in the browser
     *
     * @param script String executed script
     * @return Object the result of the operation
     */
    public Object executeJS(String script) {
        JavascriptExecutor javascriptExecutor;
        if (driver instanceof JavascriptExecutor) {
            javascriptExecutor = (JavascriptExecutor) driver;
            Object resultObject = javascriptExecutor.executeScript(script);
            logger.debug("Executed script");
            logger.debug("Script: " + script);
            if (resultObject != null)
                logger.debug("Result: " + resultObject);
            return resultObject;

        } else {
            logger.info("JS script failed");
            throw new RuntimeException("JS script failed");
        }
    }

    /**
     * Method for clicking on element
     *
     * @param by By selector for clickable element
     */
    public void click(By by) {
        WebElement element = getWebElement(by);
        Assert.assertTrue(element.isDisplayed());
        click(element);
    }


    /**
     * Clicks on given element. The element must be visible and it must have a height and width greater then 0.
     *
     * @param element WebElement element to click on
     */
    public void click(WebElement element) {
        try {
            logger.debug("click");
            element.click();
        } catch (Exception e) {
            logger.error("Cannot click on web element");
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Checks if an alert is present on page.
     *
     * @return true if exists; otherwise false
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    /**
     * Finds all elements within the current context using the given mechanism.
     *
     * @param by locating mechanism to use
     * @return a list of all WebElements, or an empty list if nothing matches
     */
    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    /**
     * Performs hovering on given element.
     *
     * @param by selector of element
     */
    public void hoverOnElement(By by) {
        logger.debug("Hover on element");
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(by)).build().perform();
    }

    /**
     * Method verifies presence of item in a select list.
     * Works only with elements with select tag.
     *
     * @param listBox  selector of an element
     * @param itemText name of item
     * @return boolean true if item is present and false otherwise
     * @throws NoSuchElementException if element was not found
     */
    public boolean isItemPresentInListBox(By listBox, String itemText) {
        List<WebElement> items = new Select(getWebElement(listBox)).getOptions();
        boolean isPresent = false;
        for (WebElement item : items) {
            if (itemText.equals(item.getText())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /**
     * The method executes JSScript location.reload() on the page
     */
    public void reloadPage() {
        logger.info("Refresh page");
        executeJS("location.reload();");
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
     * Method which verifies presence of some text on web page
     *
     * @param text text to check
     * @return true if page contains necessary text
     */
    public boolean isPageContainsText(String text) {
        return driver.getPageSource().contains(text);
    }

    /**
     * Performs click-and-hold at the location of the source element, moves to the location of
     * the target element, then releases the mouse. This method uses default 2 seconds delay during
     * the drag and drop action to complete it.
     *
     * @param source selector of element to emulate button down at
     * @param target selector of element to move to and release the mouse at
     */
    public void dragAndDrop(By source, By target) {
        dragAndDrop(source, target, 2000);
    }

    /**
     * Performs click-and-hold at the location of the source element, moves to the location of
     * the target element, then releases the mouse. This method moves the mouse to an offset from the
     * top-left corner of the element and uses default 2 seconds delay during
     * the drag and drop action to complete it.
     *
     * @param source  selector of element to emulate button down at
     * @param target  selector of element to move to and release the mouse at
     * @param xOffset offset from the top-left corner. A negative value means coordinates left from the element
     * @param yOffset offset from the top-left corner. A negative value means coordinates above the element
     */
    public void dragAndDrop(By source, By target, int xOffset, int yOffset) {
        dragAndDrop(source, target, xOffset, yOffset, 2000);
    }

    /**
     * Performs click-and-hold at the location of the source element, moves to the location of
     * the target element, then releases the mouse. This method uses delay during the drag
     * and drop action to complete it.
     *
     * @param source selector of element to emulate button down at
     * @param target selector of element to move to and release the mouse at
     * @param millis delay to sleep in milliseconds
     */
    public void dragAndDrop(By source, By target, long millis) {
        WebElement sourceElement = getWebElement(source);
        WebElement targetElement = getWebElement(target);
        Actions builder = new Actions(driver);
        builder.clickAndHold(sourceElement).moveToElement(targetElement).perform();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        builder.release(targetElement).build().perform();
    }

    /**
     * Performs click-and-hold at the location of the source element, moves to the location of
     * the target element, then releases the mouse. This method moves the mouse to an offset from the
     * top-left corner of the element and uses delay during the drag and drop action to complete it.
     *
     * @param source  selector of element to emulate button down at
     * @param target  selector of element to move to and release the mouse at
     * @param xOffset offset from the top-left corner. A negative value means coordinates left from the element
     * @param yOffset offset from the top-left corner. A negative value means coordinates above the element
     * @param millis  delay to sleep in milliseconds
     */
    public void dragAndDrop(By source, By target, int xOffset, int yOffset, long millis) {
        WebElement sourceElement = getWebElement(source);
        WebElement targetElement = getWebElement(target);
        Actions builder = new Actions(driver);
        builder.clickAndHold(sourceElement).moveToElement(targetElement, xOffset, yOffset).perform();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        builder.release(targetElement).build().perform();
    }
}