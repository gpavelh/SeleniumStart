package AllureLesson;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPageAllure {

    @Step("Переход на страницу \"Обзор\"")
    public void goToViewPage() {
        $(By.xpath("//a[@id='bank-overview' and .= 'Обзор']")).click();
    }
}
