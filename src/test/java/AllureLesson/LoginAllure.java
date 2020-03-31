package AllureLesson;


import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginAllure {

    @Step("Логин")
    public void loginToSite(String log, String pass) {
        $(By.name("username")).clear();
        $(By.name("username")).sendKeys(log);
        $(By.name("password")).clear();
        $(By.name("password")).sendKeys(pass);
        $(By.id("login-button")).click();
    }
    @Step ("Проверка языка")
    public void checkLang() {
        if ($(By.id("login-button")).getText().equals("Login")) {
            $(By.xpath("//a[.='По-русски']")).click();
        }
    }
}
