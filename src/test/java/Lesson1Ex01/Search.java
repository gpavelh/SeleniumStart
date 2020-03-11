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
        WebElement button = webDriver.findElement(By.xpath("//button[.=\"Найти\"]"));
        button.click();
        Thread.sleep(500);
        input = webDriver.findElement(By.name("text"));
        Assert.assertEquals(input.getAttribute("value"),"Hello world!");
        input = webDriver.findElement(By.xpath("//title[text()[contains(.,'hello world')]]"));
        System.out.println(input.getText());
        Assert.assertEquals(input.getText().contains("hello world"),"hello world");
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }
}
