package tests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import service.UserCreator;

public class AuthTests extends BaseTest{


    @Test
    @Order(1)
    public void loginWithWrongEmail() throws InterruptedException {
//        GmailPage gmailPage = new GmailPage(driver);
        getGmailPage()
                .openAccountManaging()
                .openAddAccountMenu()
                .chooseGoogleAccount();
        driver.context(getWebContext(driver));
        getSignInPage()
                .enterLoginAccount(UserCreator
                        .withWrongUsername()
                        .getUserLogin());
        assert (getSignInPage().getLoginError().contains("Couldn’t find your Google Account"));
    }
    @Test
    @Order(2)
    public void loginWithWrongPassword() throws InterruptedException {
        getGmailPage()
                .openAccountManaging()
                .openAddAccountMenu()
                .chooseGoogleAccount();
        driver.context(getWebContext(driver));
        getSignInPage()
                .enterLoginAccount(UserCreator.withWrongPassword().getUserLogin())
                .enterPassword(UserCreator.withWrongPassword().getPassword());
        assert (getSignInPage().getPasswordError().contains("Wrong password"));
    }

    @Test
    @Order(3)
    public void loginWithValidCredentials() throws InterruptedException {
        getGmailPage()
                .openAccountManaging()
                .openAddAccountMenu()
                .chooseGoogleAccount();
        driver.context(getWebContext(driver));
        getSignInPage()
                .enterLoginAccount(UserCreator.withWrongPassword().getUserLogin())
                .enterPassword(UserCreator.withCredentialsFromProperty().getPassword())
                .tapAgreeButton();
        driver.context("NATIVE_APP");
        getAccountManagePage()
                .tapManageAccountsBtn();
        assert (getAccountManagePage().ifAccountIsPresent(UserCreator.withCredentialsFromProperty().getUserLogin()));
    }

}
