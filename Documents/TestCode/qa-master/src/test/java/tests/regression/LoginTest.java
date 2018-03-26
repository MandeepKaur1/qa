package tests.regression;

import blocks.LoginBlock;
import common.StartUITest;
import org.testng.annotations.Test;

/**
 * Created by mandeep.
 */

public class LoginTest extends StartUITest {

    @Test
    public void login_to_test_env() throws Exception {

        LoginBlock loginBlock = new LoginBlock();
        loginBlock.login();
    }

}
