package AllureLesson; //Домашка 23

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestPageAllure {

    @BeforeClass
    public void openPage() {
        open("https://idemo.bspb.ru:");
    }


    @Test
    void firstTest() {
        //Login page
        LoginAllure login = new LoginAllure();
        login.checkLang();
        login.loginToSite("demo", "demo");

        //Code page
        CodeZoneAllure codeZone = new CodeZoneAllure();
        $(By.id("otp-code-text")).shouldHave(text(("был отправлен код подтверждения, введите его для входа")));
        codeZone.enterAuthCode("0000");

        //Main page
        MainPageAllure mainPage = new MainPageAllure();
        $(By.xpath("//div[contains(text(),'Генеральная лицензия Банка России')]")).shouldHave(text("Генеральная лицензия Банка России"));
        mainPage.goToViewPage();

        //View page
        ViewPageAllure viewPage = new ViewPageAllure();
        Assert.assertTrue(viewPage.textViewPage.isDisplayed());
        viewPage.amount.shouldBe(Condition.matchesText("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{0,2}\\s\\D"));
        viewPage.moveToAmount(viewPage.amount);
        viewPage.myMoney.shouldBe(Condition.matchesText("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{0,2}\\s\\D"));
    }
    @AfterMethod
    void closeDriver() {
        closeWebDriver();
    }
}
