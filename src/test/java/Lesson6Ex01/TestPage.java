package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //Login page
        Login login = new Login(webDriver);
        login.loginToSite("demo", "demo");

        //Code page
        WebElement textCodeZone = webDriver.findElement(By.id("otp-code-text"));
        Assert.assertTrue(textCodeZone.getText().contains("был отправлен код подтверждения, введите его для входа"));
        AuthForm authForm = new AuthForm(webDriver);
        authForm.enterAuthCode("0000");

        //Main page
        WebElement textMainPage = webDriver.findElement(By.xpath("//div[@class = 'license']"));
        Assert.assertTrue(textMainPage.getText().contains("Генеральная лицензия Банка России"));
        MainPage mainPage = new MainPage(webDriver);
        mainPage.goToViewPage();

        //View page
        WebElement textViewPage = webDriver.findElement(By.xpath("//div[1][@id = 'can-spend']/span[@class = 'text']"));
        Assert.assertTrue(textViewPage.getText().contains("Финансовая свобода"));

        //периодически тупит загрузка. запиливаем ожидание
        new WebDriverWait(webDriver, 30, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class = 'amount']")));

        WebElement amount = webDriver.findElement(By.xpath("//span[@class = 'amount']"));
        TestReg testReg = new TestReg();
        Assert.assertTrue(testReg.assertFormat(amount.getText()));

        ViewPage viewPage = new ViewPage(webDriver);
        viewPage.moveToAmount(amount);
        WebElement myMoney = webDriver.findElement(By.xpath("//small[@class = 'my-assets']"));
        String myMoneyAmount = myMoney.getText().substring(13);
        Assert.assertTrue(testReg.assertFormat(myMoneyAmount));
    }


    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }
}
