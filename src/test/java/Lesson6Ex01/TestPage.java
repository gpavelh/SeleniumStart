package Lesson6Ex01; //Домашка 20

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestPage {
    private WebDriver webDriver;

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://idemo.bspb.ru:");
    }

    @Test
    void firstTest() {
        //Login page
        Login login = new Login(webDriver);
        if (login.getLoginName().equals("Login")) {
            login.changeLang();
        }
        login.loginToSite("demo", "demo");

        //Code page
        CodeZone codeZone = new CodeZone(webDriver);
        Function function = new Function(webDriver);
        Assert.assertTrue(function.getTextFromElement(codeZone.TESTCODEZONE).contains("был отправлен код подтверждения, введите его для входа"));
        codeZone.enterAuthCode("0000");

        //Main page
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(function.getTextFromElement(mainPage.TEXTMAINPAGE).contains("Генеральная лицензия Банка России"));
        mainPage.goToViewPage();

        //View page
        ViewPage viewPage = new ViewPage(webDriver);
        Assert.assertTrue(function.getTextFromElement(viewPage.TEXTVIEWPAGE).contains("Финансовая свобода"));

        new WebDriverWait(webDriver, 30, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class = 'amount']")));
        Assert.assertTrue(function.assertFormat(function.getTextFromElement(viewPage.AMOUNT)));
        viewPage.moveToAmount(function.getWebElement(viewPage.AMOUNT));

        new WebDriverWait(webDriver, 30, 5)
                .until(ExpectedConditions.textToBePresentInElement(function.getWebElement(viewPage.MYMONEY),"Моих средств "));

        Assert.assertTrue(function.assertFormat(function.getOnlyAmount(viewPage.MYMONEY)));
    }


    @AfterClass
    void closeDriver() {
        webDriver.close();
    }
}
