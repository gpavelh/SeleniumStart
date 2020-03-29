package Lesson8Ex01;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginSelenide {

    public void loginToSite(String log, String pass) {
        $(By.name("username")).clear();
        $(By.name("username")).sendKeys(log);
        $(By.name("password")).clear();
        $(By.name("password")).sendKeys(pass);
        $(By.id("login-button")).click();
    }

    public void checkLang() {
        if ($(By.id("login-button")).getText().equals("Login")) {
            $(By.xpath("//a[.='По-русски']")).click();
        }
    }
}
