package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.UserCreator;

import java.time.Duration;

public class SignInPage extends BasePage{

    private static final Logger logger = LogManager.getRootLogger();

    @FindBy(id = "identifierId")
    private WebElement emailInput;

    @FindBy(id = "identifierNext")
    private WebElement loginNextBtn;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(id = "passwordNext")
    private WebElement passwordNextBtn;

    @FindBy(xpath = "//form//div[@aria-live='assertive']/div[contains(text(),'find your Google Account')]")
    private WebElement accountErrorMessage;

    @FindBy(xpath = "//form//span[contains(text(),'Wrong password')]")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//div[@id='signinconsentNext']//button")
    private WebElement agreeBtn;

    public SignInPage tapAgreeButton() throws InterruptedException {
        waitForElementAndClick(agreeBtn, "Can't find Agree button", DEFAULT_TIMEOUT_SEC);
        Thread.sleep(5000);
        logger.info("Account was added.");
        return this;
    }

    public SignInPage enterLoginAccount(String loginEmail) {

        waitForElementAndSendKeys(emailInput, loginEmail,"Can't enter the email", DEFAULT_TIMEOUT_SEC);
        waitForElementAndClick(loginNextBtn, "Can't tap the Next button", DEFAULT_TIMEOUT_SEC);

        return this;
    }

    public SignInPage enterPassword (String password) {
        waitForElementAndSendKeys(passwordInput, password,"Can't enter the password", DEFAULT_TIMEOUT_SEC);
        waitForElementAndClick(passwordNextBtn, "Can't tap the Next button", DEFAULT_TIMEOUT_SEC);
        return this;
    }

    public String getErrorMessage(WebElement element) {
        waitForElementIsPresent(element,"Can't find error message", DEFAULT_TIMEOUT_SEC);
      return element.getText();
    }

    public String getLoginError() {
        return getErrorMessage(accountErrorMessage);
    }

    public String getPasswordError() {
        return getErrorMessage(passwordErrorMessage);
    }

    public SignInPage(AppiumDriver driver) {
        super(driver);
    }
}
