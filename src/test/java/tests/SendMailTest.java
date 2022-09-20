package tests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import service.UserCreator;

public class SendMailTest extends BaseTest{

    private final String TEST_SUBJECT = "Gmail test";

    @Test
//    @Order(4)
    public void sendEmail() throws InterruptedException {
        getGmailPage()
             .tapComposeButton()
             .enterEmail(UserCreator.withCredentialsFromProperty().getUserLogin())
             .enterSubject(TEST_SUBJECT)
             .tapSendEmailBtn()
             .waitSendingEmail()
             .openSentFolder();
        assert (getGmailPage().testerEmailIsPresent());

    }

}
