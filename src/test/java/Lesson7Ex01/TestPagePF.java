package Lesson7Ex01; //Домашка 21

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestPagePF {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get("https://idemo.bspb.ru:");
    }

    @Test
    void firstTest() {
        //Login page
        LoginPF login = new LoginPF(webDriver);
        login.checkLang();
        login.loginToSite("demo", "demo");

        //Code page
        CodeZonePF codeZone = new CodeZonePF(webDriver);
        FunctionPF function = new FunctionPF();
        Assert.assertTrue(codeZone.testCodeZone.getText().contains("был отправлен код подтверждения, введите его для входа"));
        codeZone.enterAuthCode("0000");

        //Main page
        MainPagePF mainPage = new MainPagePF(webDriver);
        Assert.assertTrue(mainPage.textMainPage.getText().contains("Генеральная лицензия Банка России"));
        mainPage.goToViewPage();

        //View page
        ViewPagePF viewPage = new ViewPagePF(webDriver);
        Assert.assertTrue(viewPage.textViewPage.getText().contains("Финансовая свобода"));

        new WebDriverWait(webDriver, 30, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class = 'amount']")));
        Assert.assertTrue(function.assertFormat(viewPage.amount.getText()));
        viewPage.moveToAmount(viewPage.amount);

        new WebDriverWait(webDriver, 30, 5)
                .until(ExpectedConditions.textToBePresentInElement(viewPage.myMoney, "Моих средств "));
        Assert.assertTrue(function.assertFormat(viewPage.myMoney.getText().substring(13)));
    }

    @Test
    void secondTest() {
        LoginPF login = new LoginPF(webDriver);
        login.checkLang();
        login
                .loginToSiteFI("demo", "demo")
                .enterAuthCodeFI("0000")
                .goToViewPageFI()
                .assertViewPage();
    }


    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }
}
