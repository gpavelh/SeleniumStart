package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ViewPage {
    private final WebDriver webDriver;
    protected final By TEXTVIEWPAGE = By.xpath("//div[1][@id = 'can-spend']/span[@class = 'text']");
    protected final By AMOUNT = By.xpath("//span[@class = 'amount']");
    protected final By MYMONEY = By.xpath("//small[@class = 'my-assets']");

    public ViewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void moveToAmount(WebElement amount) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amount).build().perform();
    }
}
