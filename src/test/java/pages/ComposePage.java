package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComposePage extends BasePage{

    private static final Logger logger = LogManager.getRootLogger();

    @AndroidFindBy(id = "com.google.android.gm:id/to")
            private WebElement mailToField;

    @AndroidFindBy(id = "com.google.android.gm:id/subject")
    private WebElement subjectField;

    @AndroidFindBy(id = "com.google.android.gm:id/send")
    private WebElement sendBtn;

    public GmailPage tapSendEmailBtn() {
        waitForElementAndClick(sendBtn, "Can't find Send button", DEFAULT_TIMEOUT_SEC);
        logger.info("Email was sent");
        return new GmailPage(driver);
    }


    public ComposePage enterSubject(String subject) {
        waitForElementAndSendKeys(subjectField, subject, "Can't find subject field", DEFAULT_TIMEOUT_SEC);
        return this;
    }

    public ComposePage enterEmail(String email) {
        waitForElementAndSendKeys(mailToField, email, "Can't find mail-to field", DEFAULT_TIMEOUT_SEC);
        return this;
    }


    public ComposePage(AppiumDriver driver) {
        super(driver);
    }
}
