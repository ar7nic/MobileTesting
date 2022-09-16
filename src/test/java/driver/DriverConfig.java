package driver;

import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverConfig {
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static AndroidDriver driver;
    private static final Logger logger = LogManager.getRootLogger();

    public static AndroidDriver createDriverInstance() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("platformVersion","10");
            capabilities.setCapability("appium:deviceName", "Android emulator");
            capabilities.setCapability("appium:automationName", "UIAutomator2");
            capabilities.setCapability("chromedriverExecutableDir","C:\\Projects\\Java\\AppiumHomework\\AppiumHomework\\src\\main\\resources");
            capabilities.setCapability("chromedriverChromeMappingFile","C:\\Projects\\Java\\AppiumHomework\\AppiumHomework\\src\\main\\resources\\map.json");
            capabilities.setCapability("udid", "emulator-5554");

            driver = new AndroidDriver (new URL(APPIUM), capabilities);

        } catch (MalformedURLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return driver;
    }


}
