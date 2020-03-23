package Lesson8Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class CodeZoneSelenide {

    public void enterAuthCode(String code) {
        $(By.id("otp-code")).clear();
        $(By.id("otp-code")).sendKeys(code);
        $(By.id("login-otp-button")).click();
    }
}
