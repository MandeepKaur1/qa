package regression.com.pages;

import org.openqa.selenium.By;

public class LoginPage {
    public static final By txtDomainName = By.cssSelector("#domain");
    public static final By btnContinue = By.cssSelector("#__next > div > div > div.content > div.content-center > div > div > div.form > form > span:nth-child(2) > button");
    public static final By btnContinueLogin = By.cssSelector("#__next > div > div > div.content > div.content-center > div > div > div.form > form > span > button");

    public static final By txtUserName = By.cssSelector("#username");
    public static final By txtUserPassword = By.cssSelector("#password");
    public static final By dropDownTenantLocation = By.id("location");
    public static final By btnLogin = By.cssSelector("div.submit-button");
    public static final By lnkForgetPassword = By.cssSelector("div:nth-child(2) > div > a");
    public static final By lnkHelp = By.cssSelector("div.page-footer > p");


}
