import com.pointcare.blocks.LoginBlock;
import com.pointcare.pages.LoginPage;
import com.pointcare.tests.BaseUITest;
import com.pointcare.tests.SetBrowser_SetEnv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by mandeep on 6/2/17.
 */
public class test extends BaseUITest1 {
    WebDriver driver = new ChromeDriver();

    @org.junit.Test
    public void test1() {

        driver.get("https://staging.pointcare.com");
        System.out.println("Enter Domain Name");
        setText1(LoginPage.txtDomainName,user);
        click(LoginPage.btnContinue);
        System.out.println("Enter User Name");
        setText1(LoginPage.txtUserName, "");
       // WebDriver driver = new ChromeDriver();
        //driver.get("https://staging.pointcare.com");
        //driver.findElement(By.cssSelector("#domain")).sendKeys("clinicasierravista");
        //click(LoginPage.btnContinue);


    }

}
