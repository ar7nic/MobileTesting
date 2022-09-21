package ulils;


import driver.DriverConfig;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class TestListener implements TestWatcher {
    private Logger log = LogManager.getRootLogger();

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
    }


//    public void onTestFailure(TestWatcher testWatcher) {
//        saveScreenshot();
//    }
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        saveScreenshot();
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        saveScreenshot();
        DriverConfig.closeDriver();
    }



    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) DriverConfig
                .createDriverInstance())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                    + getCurrentTimeAsString() +
                    ".png"));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}
