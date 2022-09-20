package tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import service.UserCreator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTests extends BaseTest{


    @Test
    @Order(1)
    public void loginWithWrongEmail() throws InterruptedException {
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
