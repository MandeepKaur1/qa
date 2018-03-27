package regression.com.blocks;

import regression.com.pages.LoginPage;
import regression.com.test.BaseUITest;

import static jxl.biff.FormatRecord.logger;

public class LoginBlock extends BaseUITest{
    private String tenant;
    private String user;
    private String password;

    public void loginAs_clinicaSierraVista_user() {
        tenant = "";
        user = "";
        password = "";

        logger.info("Set Environment");
        getEnvironment();

        //click(LoginPage.screenClick);
        logger.info("Set Tenant name");
        setText(LoginPage.txtDomainName, tenant);
        logger.info("Click on Continue Button");
        click(LoginPage.btnContinue);

        logger.info("Set UserName");
        setText(LoginPage.txtUserName, user);

        logger.info("Set UserPassword");
        setText(LoginPage.txtUserPassword, password);

        logger.info("Click Continue Button");
        click(LoginPage.btnContinueLogin);
    }
}
