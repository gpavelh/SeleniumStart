package Lesson7Ex01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPagePF {

    private final WebDriver webDriver;

    public MainPagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//a[@id='bank-overview' and .= 'Обзор']")
    private WebElement viewButton;
    @FindBy(xpath = "//div[contains(text(),'Генеральная лицензия Банка России')]")
    protected WebElement textMainPage;


    public void goToViewPage() {
        viewButton.click();
    }
}
