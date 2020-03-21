package Lesson7Ex01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewPagePF {

    public ViewPagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    private final WebDriver webDriver;
    @FindBy(xpath = "//div[1][@id = 'can-spend']/span[@class = 'text']")
    protected WebElement TextViewPage;
    @FindBy(xpath = "//span[@class = 'amount']")
    protected WebElement Amount;
    @FindBy(xpath = "//small[@class = 'my-assets']")
    protected WebElement MyMoney;


    public void moveToAmount(WebElement amount) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amount).build().perform();
    }
}
