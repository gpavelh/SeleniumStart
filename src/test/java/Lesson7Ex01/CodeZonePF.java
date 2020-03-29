package Lesson7Ex01;

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
    private WebElement codeField;
    @FindBy(id = "login-otp-button")
    private WebElement button;
    @FindBy(id = "otp-code-text")
    protected WebElement testCodeZone;


    public void enterAuthCode(String code) {
        codeField.clear();
        codeField.sendKeys(code);
        button.click();
    }
    public MainPagePF enterAuthCodeFI(String code) {
        codeField.clear();
        codeField.sendKeys(code);
        button.click();
        return new MainPagePF(webDriver);
    }
}
