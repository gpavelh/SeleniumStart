package Lesson1Ex02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SavkkSite {
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @Test
    void openPage() throws InterruptedException {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        WebElement button = webDriver.findElement(By.id("button"));
        button.click();
        WebElement clickMe = webDriver.findElement(By.id("first"));
//        Thread.sleep(1000);
        clickMe.click();
        WebElement clickMeToo = webDriver.findElement(By.xpath("//input[@value='Click me too!']"));
//        Thread.sleep(1000);
        clickMeToo.click();
        WebElement goBack = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBack.getText(), "Great! Return to menu");
//        Thread.sleep(1000);
        goBack.click();
//        Thread.sleep(1000);
        WebElement checkBoxes = webDriver.findElement(By.id("checkbox"));
        checkBoxes.click();

    }

    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }


}
