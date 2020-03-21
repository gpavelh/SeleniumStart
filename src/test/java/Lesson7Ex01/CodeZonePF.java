package Lesson7Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CodeZonePF {

    private final WebDriver webDriver;

    public CodeZonePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "otp-code")
    private WebElement CodeField;
    @FindBy(id = "login-otp-button")
    private WebElement Button;
    @FindBy(id = "otp-code-text")
    protected WebElement TestCodeZone;


    public void enterAuthCode(String code) {
        CodeField.clear();
        CodeField.sendKeys(code);
        Button.click();
    }
}
