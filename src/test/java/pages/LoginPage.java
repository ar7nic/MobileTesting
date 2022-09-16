package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.gm:id/account_setup_label']")
    private WebElement googleAccount;

    public void chooseGoogleAccount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForElementAndClick(googleAccount, "Couldn't find 'Google account' menu item", DEFAULT_TIMEOUT_SEC);
        Thread.sleep(4000);
    }



    public LoginPage(AppiumDriver driver) {
        super(driver);
    }
}
