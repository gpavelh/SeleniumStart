package Lesson1Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement inputValue = webDriver.findElement(By.id("text"));
        inputValue.sendKeys("Руддщ цщкдв!");
        webDriver.findElement(By.xpath("//button[.=\"Найти\"]")).click();

        Boolean input = new WebDriverWait(webDriver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions
                        .attributeToBe(By.xpath("//input[@name = 'text']"), "value", "Hello world!"));
        Assert.assertTrue(input, "Hello world!");

        inputValue = webDriver.findElement(By.name("text"));
        Assert.assertEquals(inputValue.getAttribute("value"), "Hello world!");
        Assert.assertTrue(webDriver.getTitle().contains("Hello world!"));
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }
}
