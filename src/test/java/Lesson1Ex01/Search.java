package Lesson1Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Search {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test
    void YandexSearch() throws InterruptedException {
        webDriver.get("https://ya.ru");
        WebElement input = webDriver.findElement(By.id("text"));
        input.sendKeys("Руддщ цщкдв!");
        webDriver.findElement(By.xpath("//button[.=\"Найти\"]")).click();
        Thread.sleep(500); //тупой ноут. долго проводит автозамену в поле поиска
        input = webDriver.findElement(By.name("text"));
        Assert.assertEquals(input.getAttribute("value"), "Hello world!");
        Assert.assertTrue(webDriver.getTitle().contains("Hello world!"));
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }
}
