package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AccountManagePage extends BasePage{

    private static final Logger logger = LogManager.getRootLogger();

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Add')]")
    private WebElement addAccountBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Manage')]")
    private WebElement manageAccountBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
    private List<WebElement> listOfAccounts;

    public boolean ifAccountIsPresent(String userEmail) {

        for (WebElement account: listOfAccounts) {
        if (account.getText().equals(userEmail)) {
            logger.info("Account [" + userEmail + "] is present");
            return true;}
        }
        return false;
    }

    public AccountManagePage tapManageAccountsBtn() {
        waitForElementAndClick(manageAccountBtn, "Can't find Manage accounts button", DEFAULT_TIMEOUT_SEC);
        return this;
    }

    public LoginPage openAddAccountMenu() {
        waitForElementAndClick(addAccountBtn, "Couldn't find 'Add another account' menu item", DEFAULT_TIMEOUT_SEC);
        return new LoginPage(driver);
    }
    public AccountManagePage(AppiumDriver driver) {
        super(driver);
    }
}
