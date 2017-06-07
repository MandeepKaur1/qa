import com.pointcare.common.Browser;
import com.pointcare.common.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static jxl.biff.FormatRecord.logger;


/**
 * The BaseUITest class is the main class for all UI test scripts.
 */
public class BaseUITest1 {

    private final Browser browser = new Browser();
    WebDriver driver;

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
}
