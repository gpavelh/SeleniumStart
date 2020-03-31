package AllureLesson;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CodeZoneAllure {

    @Step("Ввод кода авторизации")
    public void enterAuthCode(String code) {
        $(By.id("otp-code")).clear();
        $(By.id("otp-code")).sendKeys(code);
        $(By.id("login-otp-button")).click();
    }
}
