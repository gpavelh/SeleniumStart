package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private final WebDriver webDriver;
    private final By LOGIN = By.name("username");
    private final By PASSWORD = By.name("password");
    private final By BUTTON = By.id("login-button");
    private final By LANG = By.xpath("//a[.='По-русски']");
    private final By LOGINBUTTON = By.id("login-button");

    public Login(WebDriver webDriver) {

        this.webDriver = webDriver;
    }

    public void loginToSite(String log, String pass) {
        webDriver.findElement(LOGIN).clear();
        webDriver.findElement(LOGIN).sendKeys(log);
        webDriver.findElement(PASSWORD).clear();
        webDriver.findElement(PASSWORD).sendKeys(pass);
        webDriver.findElement(BUTTON).click();
    }

    public void changeLang() {
        webDriver.findElement(LANG).click();
    }

    public String getLoginName() {
        return webDriver.findElement(LOGINBUTTON).getText();
    }
}
