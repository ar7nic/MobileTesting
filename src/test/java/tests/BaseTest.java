package tests;


import driver.DriverConfig;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import pages.*;
import ulils.TestListener;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;


@ExtendWith(TestListener.class)
public class BaseTest {

    public static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {



    }
    @BeforeEach
    public void launchApp() throws InterruptedException {
        new DriverConfig();
        driver = DriverConfig.createDriverInstance();
        Thread.sleep(1000);
        driver.startActivity(new Activity("com.google.android.gm", ".ConversationListActivityGmail"));

    }



    @AfterEach()
    public void close() {

        driver.terminateApp("com.android.settings");
        driver.terminateApp("com.google.android.gms");
        if (driver != null) {
            DriverConfig.closeDriver();
        }

    }


        public GmailPage getGmailPage() {
        return new GmailPage(driver);
        }

        public AccountManagePage getAccountManagePage() {
        return new AccountManagePage(driver);
        }

        public LoginPage getLoginPage() {
        return new LoginPage(driver);
        }

        public SignInPage getSignInPage() {
        return new SignInPage(driver);
        }

        public ComposePage getComposePage() {
        return new ComposePage(driver);
        }

    @Nullable
    public String getWebContext(AndroidDriver driver) {
        ArrayList<String> contexts = new ArrayList<String>(driver.getContextHandles());
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (!contextName.equals("NATIVE_APP")) {
                System.out.println(contextName);
                return contextName;
            }
        }
        return null;
    }

}
