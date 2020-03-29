package Lesson8Ex01;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ViewPageSelenide {

    protected SelenideElement amount = $(By.xpath("//span[@class = 'amount']"));
    protected SelenideElement myMoney = $(By.xpath("//small[@class = 'my-assets']"));
    protected SelenideElement textViewPage = $(By.xpath ("//div/span[contains(text(),'свобода')]"));



    public void moveToAmount(SelenideElement element) {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element).build().perform();
    }
}
