package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CodeZone {
    private final WebDriver webDriver;
    private final By CODEFIELD = By.id("otp-code");
    private final By BUTTON = By.id("login-otp-button");
    protected final By TESTCODEZONE = By.id("otp-code-text");

    public CodeZone(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void enterAuthCode(String code){
        webDriver.findElement(CODEFIELD).clear();
        webDriver.findElement(CODEFIELD).sendKeys(code);
        webDriver.findElement(BUTTON).click();
    }
}
