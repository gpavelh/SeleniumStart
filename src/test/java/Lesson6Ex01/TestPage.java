package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPage {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://idemo.bspb.ru:");
    }

    @Test
    void firstPage() {
        Login login = new Login(webDriver);
        login.loginToSite("demo","demo");
        AuthForm authForm = new AuthForm(webDriver);
        authForm.enterAuthCode("0000");
        MainPage mainPage = new MainPage(webDriver);
        mainPage.goToViewPage();
    }


    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }
}
