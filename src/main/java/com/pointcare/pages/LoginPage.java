package com.pointcare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by mandeep.
 */
public class LoginPage {

    public static final By txtDomainName = By.cssSelector("#domain");
    public static final By btnContinue = By.cssSelector("div.submit-button > button");

    public static final By txtUserName = By.cssSelector("#username");
    public static final By txtUserPassword = By.cssSelector("#password");
    public static final By btnLogin = By.cssSelector("div.submit-button");
    public static final By lnkForgetPassword = By.cssSelector("div:nth-child(2) > div > a");


}
