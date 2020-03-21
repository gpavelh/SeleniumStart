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
    private WebElement Login;

    @FindBy(name = "password")
    private WebElement Password;

    @FindBy(id = "login-button")
    protected WebElement Button;

    @FindBy(xpath = "//a[.='По-русски']")
    private WebElement Lang;


    public void loginToSite(String log, String pass) {
        Login.clear();
        Login.sendKeys(log);
        Password.clear();
        Password.sendKeys(pass);
        Button.click();
    }

    public void changeLang() {
        Lang.click();
    }
}
