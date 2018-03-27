package blocks;

import pages.LoginPage;
import tests.BaseUITest;
import org.testng.annotations.Test;

import static jxl.biff.FormatRecord.logger;


/**
 * Created by mandeep.
 */
public class LoginBlock extends BaseUITest {

    private String tenantName = "";
    private String userName = "";
    private String password = "";


    @Test
    public void login() throws Exception {

        logger.info("Enter Domain Name");
        setText(LoginPage.txtDomainName, tenantName);
        click(LoginPage.btnContinue);

        logger.info("Enter User Name");
        setText(LoginPage.txtUserName, userName);

        logger.info("Enter User Password Name");
        setText(LoginPage.txtUserPassword, password);
        click(LoginPage.btnLogin);
    }
}
