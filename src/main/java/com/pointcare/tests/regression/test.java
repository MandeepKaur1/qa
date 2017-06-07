package com.pointcare.tests.regression;

import com.pointcare.blocks.LoginBlock;
import com.pointcare.pages.LoginPage;
import com.pointcare.tests.BaseUITest;
import com.pointcare.tests.SetBrowser_SetEnv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by mandeep on 6/2/17.
 */
public class test extends BaseUITest {
    //@BeforeTest
    public void test() {
        new SetBrowser_SetEnv().select_ChromeBrowser();
    }
    @Test
    public void test1() throws Exception {
        LoginBlock loginBlock = new LoginBlock();
       loginBlock.login( );

       // WebDriver driver = new ChromeDriver();
        //driver.get("https://staging.pointcare.com");
        //driver.findElement(By.cssSelector("#domain")).sendKeys("clinicasierravista");
        //click(LoginPage.btnContinue);


    }

}
