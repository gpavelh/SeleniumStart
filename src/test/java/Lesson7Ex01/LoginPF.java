package Lesson7Ex01;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPF {

    private final WebDriver webDriver;

    public LoginPF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name = "username")
    private WebElement login;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    protected WebElement button;

    @FindBy(xpath = "//a[.='По-русски']")
    private WebElement lang;


    public void loginToSite(String log, String pass) {
        login.clear();
        login.sendKeys(log);
        password.clear();
        password.sendKeys(pass);
        button.click();
    }

    public void changeLang() {
        lang.click();
    }
}
