package Lesson2Ex01;

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
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.get("https://savkk.github.io/selenium-practice/");
    }

    @Test
    void openPage() throws InterruptedException {
        //Button page
        webDriver.findElement(By.id("button")).click();
        webDriver.findElement(By.id("first")).click();
        webDriver.findElement(By.xpath("//input[@value='Click me too!']")).click();
        WebElement goBack = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBack.getText(), "Great! Return to menu");
        goBack.click();

        //Checkbox and Radio
        webDriver.findElement(By.id("checkbox")).click();
        //CheckBoxes
        webDriver.findElement(By.xpath("//input[@type = 'checkbox' and @value = 'one']")).click();
        webDriver.findElement(By.xpath("//input[@type = 'checkbox' and @value = 'two']")).click();
        webDriver.findElement(By.id("go")).click();
        WebElement resultCheckBox = webDriver.findElement((By.xpath("//label[@id='result']")));
        Assert.assertEquals(resultCheckBox.getText(), "one two");

        //Radio button
        webDriver.findElement(By.xpath("//input[@type ='radio' and @value = 'two']")).click();
        webDriver.findElement(By.id("radio_go")).click();
        WebElement resultRadioButton = webDriver.findElement(By.xpath("//label[@id='radio_result']"));
        Assert.assertEquals(resultRadioButton.getText(), "two");
        WebElement goBackAgain = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBackAgain.getText(), "Great! Return to menu");
        goBackAgain.click();

    }

    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }
}
