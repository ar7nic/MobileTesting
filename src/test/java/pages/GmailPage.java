package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class GmailPage extends BasePage {

    private static final Logger logger = LogManager.getRootLogger();

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.google.android.gm:id/identity_disc_menu_item']")
    private WebElement accountIcon;

    @AndroidFindBy(id = "com.google.android.gm:id/compose_button")
    private WebElement composeBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'unsent')]")
    private WebElement unsent;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
    private WebElement burgerMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    private WebElement sentFolder;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Gmail test')]")
    private WebElement emailWithTestSubject;

    public boolean testerEmailIsPresent() {
      return  waitForElementIsPresent(emailWithTestSubject, "Can't find tester mail", DEFAULT_TIMEOUT_SEC).isDisplayed();
    }


    public GmailPage openSentFolder() {
        waitForElementAndClick(burgerMenu,"Can't find Burger menu", DEFAULT_TIMEOUT_SEC);
        waitForElementAndClick(sentFolder,"Can't find Sent folder", DEFAULT_TIMEOUT_SEC);
        return this;
    }

    public ComposePage tapComposeButton() throws InterruptedException {
        waitForElementAndClick(composeBtn, "Can't find Compose button", DEFAULT_TIMEOUT_SEC);
        logger.info("Creating Email");
        return new ComposePage(driver);
    }

    public GmailPage waitSendingEmail() throws InterruptedException {
        Thread.sleep(6000);
        waitForElementToDisappear(unsent, "Can't find Unsent message", 50);
        return this;
    }

    public AccountManagePage openAccountManaging() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementToBeClickable(accountIcon,"Couldn't find account icon", DEFAULT_TIMEOUT_SEC);
        waitForElementAndClick(accountIcon, "Couldn't find account icon", DEFAULT_TIMEOUT_SEC);
        return new AccountManagePage(driver);
    }

    public GmailPage(AppiumDriver driver) {
        super(driver);
    }
}
