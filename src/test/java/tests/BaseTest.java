package tests;


import driver.DriverConfig;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import pages.*;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
//    private static final String APPIUM = "http://localhost:4723/wd/hub";
    //    Logger logger = new Logger();
    public static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {

//        new DriverConfig();
//        driver = DriverConfig.createDriverInstance();

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
//        driver.terminateApp("com.google.android.gm");
        driver.terminateApp("com.android.settings");
        driver.terminateApp("com.google.android.gms");
        if (driver != null) {
            driver.quit();
        }

    }

    @AfterAll
    public static void tearDown() {

//        if (driver != null) {
//            driver.quit();
//        }
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

//    public GmailPage getGmailPage() {
//        return new GmailPage(driver);
//    }
//
//    public AccountManagePage getAddAccountPage() {
//        return new AccountManagePage(driver);
//    }
//
//    public LoginPage getLoginPage() {
//        return new LoginPage(driver);
//    }




//    @Test
//    public void terminate() {
////        driver.terminateApp(".accountsettings.mg.ui.main.MainActivity");
////        driver.resetApp();
////        ApplicationState state = driver.queryAppState("com.android.gm");
//////This code will press your android device's recent button :
////        System.out.println(state);
//        driver.startActivity(new Activity("com.google.android.gm", ".ConversationListActivityGmail"));
//
//
//        String activity = driver.currentActivity();
//        String packageApp = driver.getCurrentPackage();
//        System.out.println(packageApp);
//        System.out.println(activity);
//
////.ConversationListActivityGmail
//
//    }
}
