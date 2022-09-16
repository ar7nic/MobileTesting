package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class BasePage {

    protected AppiumDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    public final long DEFAULT_TIMEOUT_SEC = 20;

//    @Nullable
//    public String getWebContext(AndroidDriver driver) {
//        ArrayList<String> contexts = new ArrayList<String>(driver.getContextHandles());
//        Set<String> contextNames = driver.getContextHandles();
//        for (String contextName : contextNames) {
//            if (!contextName.equals("NATIVE_APP")) {
//                System.out.println(contextName);
//                return contextName;
//            }
//        }
//        return null;
//    }

    public BasePage (AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public WebElement waitForElementAndClick(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            logger.error(errorMessage);
            throw new RuntimeException(e);
        }
        return  element;
    }

//    private void fluentWait(WebElement element, long timeout) {
//        Wait wait = new AppiumFluentWait(driver)
//                .withTimeout(Duration.ofSeconds(timeout))
//                .pollingEvery(Duration.ofMillis(5))
//                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }

//    public void waitForElementToBePresent(WebElement element, String errorMessage, long timeoutInSeconds) {
//        Wait wait = new FluentWait(driver)
//                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
//                .pollingEvery(Duration.ofMillis(5))
//                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
//                .ignoring(TimeoutException.class)
//                .withMessage(errorMessage);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }
//        public WebElement waitForElementToBePresent(By element, String errorMessage, long timeoutInSeconds) {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//            try {
//                return wait.until(ExpectedConditions.presenceOfElementLocated(element));
//            } catch (Exception e) {
//                logger.error(errorMessage);
//                throw new RuntimeException(e);
//            }
//    }


    public WebElement waitForElementToBeClickable(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }
    public WebElement waitForElementIsPresent(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error(errorMessage);
            throw new RuntimeException(e);
        }
    }

    public boolean waitForElementToDisappear(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            logger.error(errorMessage);
            throw new RuntimeException(e);
        }
    }


//    public String waitForElementAndGetText(By element, String errorMessage, long timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//
//    }

    public WebElement waitForElementAndSendKeys(WebElement element, String value, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
            element.sendKeys(value);
        } catch (Exception e) {
            logger.error(errorMessage);
            throw new RuntimeException(e);
        }
        return element;
    }


    public void waitVisibilityOfElement(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitElementIsClickable(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public String getErrorMessage(WebElement element) {
        waitForElementIsPresent(element,"Can't find error message", DEFAULT_TIMEOUT_SEC);
        return element.getText();
    }

}
