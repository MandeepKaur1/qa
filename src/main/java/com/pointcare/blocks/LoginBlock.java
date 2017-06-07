package com.pointcare.blocks;

import com.pointcare.common.Browser;
import com.pointcare.common.FrameworkConstants;
import com.pointcare.pages.LoginPage;
import com.pointcare.tests.BaseUITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static jxl.biff.FormatRecord.logger;

/**
 * Created by mandeep.
 */
public class LoginBlock extends BaseUITest
{

    WebDriver driver = new ChromeDriver();

    @Test
    public void login() throws Exception{
        String user = "clinicasierravista";

        driver.get("https://staging.pointcare.com");
        System.out.println("Enter Domain Name");
       setText(LoginPage.txtDomainName,user);
        click(LoginPage.btnContinue);
        System.out.println("Enter User Name");
       setText1(LoginPage.txtUserName, "productadmin@pointcare.com");
        //logger.info("Enter User Password Name");

        //setText(LoginPage.txtUserPassword,"company#123");
       // click(LoginPage.btnLogin);
    }


}
