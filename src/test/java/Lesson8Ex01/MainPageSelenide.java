package Lesson8Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class MainPageSelenide {

    public void goToViewPage() {
        $(By.xpath("//a[@id='bank-overview' and .= 'Обзор']")).click();
    }
}
