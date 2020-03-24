package tests;

import org.testng.annotations.Listeners;
import utils.Listeners.TestListener;
import org.testng.annotations.Test;
import pages.UkrNetHomePage;
import pages.LoginPage;
import utils.GenerateRandomData;
import io.qameta.allure.*;

@Listeners({TestListener.class})
@Epic("Functional test")
@Feature("Login test")
public class SendEmailTest extends BaseTest {

    GenerateRandomData generateRandomData = new GenerateRandomData();
    String login = "UAtestAPI@ukr.net";
    String password = "";
    String receiver = generateRandomData.generateRandomString(5) + "@yopmail.com";
    String title = generateRandomData.generateRandomString(10);
    String emailBody = generateRandomData.generateRandomString(150);


    @Test(description = "Checking login to the email and sending the mail")
    public void checkingLoginToTheEmailAndSendingTheMail() {
        LoginPage loginPage = new LoginPage(driver);
        UkrNetHomePage ukrNetHomePage;
        loginPage.goToLoginPage();
        ukrNetHomePage = loginPage.loginToUkrNet(login, password);
        ukrNetHomePage.createLetter(receiver, title, emailBody);
        ukrNetHomePage.verifySendingLetterPopup();
        ukrNetHomePage.checkingPresenceEmailInSentFolder(title);
    }
}
