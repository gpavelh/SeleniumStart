package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver webDriver;
    private final By VIEWBUTTON = By.xpath("//a[@id='bank-overview' and .= 'Обзор']");
    protected final By TEXTMAINPAGE = By.xpath("//div[contains(text(),'Генеральная лицензия Банка России')]");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void goToViewPage() {
        webDriver.findElement(VIEWBUTTON).click();
    }
}
