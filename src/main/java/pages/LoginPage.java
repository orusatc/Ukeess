package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    //*********Constructor*********
    public LoginPage (WebDriver driver) {
        super(driver);
    }
    //*********Page Variables*********
    String loginURL = "https://accounts.ukr.net/login?lang=ui";

    //*********Web Elements*********
    By signInButtonBy = By.cssSelector("button.form__submit");
    By usernameBy = By.id("id-l");
    By passwordBy = By.id("id-p");

    //*********Page Methods*********
    @Step("Open login page")
    public LoginPage goToLoginPage (){
        driver.get(loginURL);
        return this;
    }

    @Step("Login to mail with login: {0}, password: {1} ")
    public UkrNetHomePage loginToUkrNet (String username, String password){
        //Enter Username(Email)
        writeText(usernameBy,username);
        //Enter Password
        writeText(passwordBy, password);
        //Click Login Button
       return goToUkrNet();
    }

    @Step("Go to UkrNetHomePage")
    public UkrNetHomePage goToUkrNet (){
        click(signInButtonBy);
        return new UkrNetHomePage(driver);
    }
}