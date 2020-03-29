package Lesson7Ex01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ViewPagePF {

    public ViewPagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    private final WebDriver webDriver;
    @FindBy(xpath = "//div/span[contains(text(),'свобода')]")
    protected WebElement textViewPage;
    @FindBy(xpath = "//span[@class = 'amount']")
    protected WebElement amount;
    @FindBy(xpath = "//small[@class = 'my-assets']")
    protected WebElement myMoney;


    public void moveToAmount(WebElement amount) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amount).build().perform();
    }
    public void assertViewPage() {
        Assert.assertTrue(textViewPage.isDisplayed());
    }
}
