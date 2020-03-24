package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class UkrNetHomePage extends BasePage {

    //*********Constructor*********
    public UkrNetHomePage(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements*********
    By writeLetterButtonBy = By.cssSelector("button.default");
    By toBy = By.cssSelector(".sendmsg__form-label-field.auto.cropped.ui-sortable input.input");
    By titleBy = By.cssSelector("[name='subject']");
    By bodyBy = By.id("tinymce");
    By bodyFrameBy = By.id("mce_0_ifr");
    By sendButtonBy = By.className("send");
    By sentMailPopUpBy = By.className("sendmsg__ads-ready");
    By sentFolder = By.id("10001");

    //*********Page Methods*********

    /**
     * @param receiver -- persons who gets the mail
     * @param title    -- email's title
     * @param letter   -- email's content
     */
    @Step("Creating and sending email to: {0} with title: {1} and body: {2}")
    public void createLetter(String receiver, String title, String letter) {
        click(writeLetterButtonBy);
        writeText(toBy, receiver);
        writeText(titleBy, title);
        writeTextToFrame(bodyFrameBy, bodyBy, letter);
        click(sendButtonBy);
    }

    @Step("Verify sending letter Popup presence")
    public UkrNetHomePage verifySendingLetterPopup() {
        assertTrue(sentMailPopUpBy, !readText(sentMailPopUpBy).isEmpty(), "Pop up about sent message doesn't shown");
        return this;
    }

    /**
     * @param folderName   -- name of folder in which is necessary to check email presence
     * @param emailSubject -- subject of email which need to check
     * @return UkrNetHomePage;
     */

    @Step("Checking presence email by subject: {1} in folder")
    public UkrNetHomePage checkingPresenceEmailInFolder(By folderName, String emailSubject, String message) {
        click(folderName);
        By titleBy = By.xpath("//a[contains(text(), '" + emailSubject + "')]");
        assertTrue(titleBy, readText(titleBy).contains(emailSubject), message);
        return this;
    }

    @Step("Checking presence email in Send folder")
    public UkrNetHomePage checkingPresenceEmailInSentFolder(String emailSubject) {
        return checkingPresenceEmailInFolder(sentFolder, emailSubject, "Email by subject: " + emailSubject + " is absent in folder: Sent");
    }

}