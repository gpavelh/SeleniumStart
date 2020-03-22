package Lesson7Ex01; //Домашка 21

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestPagePF {
    private WebDriver webDriver;

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get("https://idemo.bspb.ru:");
    }

    @Test
    void firstTest() {
        //Login page
        LoginPF login = new LoginPF(webDriver);
        if (login.button.getText().equals("Login")) {
            login.changeLang();
        }
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


    @AfterClass
    void closeDriver() {
        webDriver.close();
    }
}
