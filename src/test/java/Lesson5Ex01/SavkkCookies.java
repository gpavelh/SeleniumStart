package Lesson5Ex01; //Домашка 19

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.*;

public class SavkkCookies {
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://savkk.github.io/selenium-practice/");
    }


    @Test
    void buttonTest() {
        //Button page
        webDriver.findElement(By.id("button")).click();
        webDriver.findElement(By.id("first")).click();
        webDriver.findElement(By.xpath("//input[@value='Click me too!']")).click();
        WebElement goBack = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBack.getText(), "Great! Return to menu");
        goBack.click();
        Assert.assertEquals(webDriver.manage().getCookieNamed("button").getName(),"button");
    }

    @Test
    void checkBoxAndRadioTest() {
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
        Assert.assertEquals(webDriver.manage().getCookieNamed("checkboxes").getName(), "checkboxes");
    }

    @Test
    void selectTest() {
        //Select hero
        webDriver.findElement(By.id("select")).click();
        WebElement selectHero = webDriver.findElement(By.xpath("//select[@name = 'hero']"));
        Select hero = new Select(selectHero);
        hero.selectByVisibleText("Grady Booch");

        //Select lang
        WebElement selectLang = webDriver.findElement(By.xpath("//select[@name = 'languages']"));
        Select lang = new Select(selectLang);
        lang.selectByValue("Java");

        //Get Results
        webDriver.findElement(By.id("go")).click();
        WebElement choiceResult = webDriver.findElement(By.xpath("//label[.='Java']"));
        Assert.assertEquals(choiceResult.getText(), "Java");
        WebElement goBackAgain2 = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBackAgain2.getText(), "Great! Return to menu");
        goBackAgain2.click();
        Assert.assertEquals(webDriver.manage().getCookieNamed("select").getName(), "select");
    }

    @Test
    void formAndIframeTest() {
        //Form
        webDriver.findElement(By.id("form")).click();
        webDriver.findElement(By.xpath("//div[1]/input[@type='text']")).sendKeys("Pavel");
        webDriver.findElement(By.xpath("//div[2]/input[@type='text']")).sendKeys("Glinyany");
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("gh7777@mail.ru");
        webDriver.findElement(By.xpath("//input[1][@type = 'radio']")).click();
        webDriver.findElement(By.xpath("//div[5]/input[@type='text']")).sendKeys("Moscow");
        webDriver.findElement(By.xpath("//input[@type = 'file']")).sendKeys("C:/Users/Tanika/Desktop/Новый текстовый документ.txt");
        webDriver.findElement(By.xpath("//textarea [@cols = '50']")).sendKeys("Ton Tom");
        webDriver.findElement(By.xpath("//input[@type = 'submit']")).click();
        WebElement goBackFromForm = webDriver.findElement(By.xpath("//a[contains(text(),'Great')]"));
        Assert.assertEquals(goBackFromForm.getText(), "Great! Return to menu");
        goBackFromForm.click();
        Assert.assertEquals(webDriver.manage().getCookieNamed("form").getName(), "form");

        //IFrame
        webDriver.findElement(By.id("iframe")).click();
        webDriver.switchTo().frame("code-frame");
        WebElement codeField = webDriver.findElement(By.id("code"));
        String code = codeField.getText().substring(14);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.name("code")).sendKeys(code);
        webDriver.findElement(By.name("ok")).click();
        WebElement goBackFromIframe = webDriver.findElement(By.xpath("//a[contains(text(),'Great')]"));
        Assert.assertEquals(goBackFromIframe.getText(), "Great! Return to menu");
        goBackFromIframe.click();
        Assert.assertEquals(webDriver.manage().getCookieNamed("iframe").getName(), "iframe");
    }

    @Test
    void promptAlertAndConfirm() {
        //Prompt, Alert and Confirm
        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.xpath("//button[@class = 'get']")).click();
        Alert alertPass = webDriver.switchTo().alert();
        String password = alertPass.getText().substring(15);
        alertPass.accept();
        webDriver.findElement(By.xpath("//button[@class = 'set']")).click();
        Alert alertPassEnter = webDriver.switchTo().alert();
        alertPassEnter.sendKeys(password);
        alertPassEnter.accept();
        WebElement great = webDriver.findElement(By.xpath("//label[.='Great!']"));
        Assert.assertEquals(great.getText(), "Great!");
        WebElement returnToMenu = webDriver.findElement(By.xpath("//button[@class ='return']"));
        Assert.assertTrue(returnToMenu.isDisplayed());
        returnToMenu.click();
        webDriver.switchTo().alert().accept();
        Assert.assertEquals(webDriver.manage().getCookieNamed("alerts").getName(), "alerts");
    }

    @Test(enabled = false)
    void promptAlertAndConfirmNegative() throws InterruptedException {
        //Prompt, Alert and Confirm NegativeTest
        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.xpath("//button[@class = 'get']")).click();
        Alert alertPass = webDriver.switchTo().alert();
        String password = alertPass.getText();
        alertPass.accept();
        webDriver.findElement(By.xpath("//button[@class = 'set']")).click();
        Alert alertPassEnter = webDriver.switchTo().alert();
        alertPassEnter.sendKeys(password);
        alertPassEnter.accept();
        Thread.sleep(3000);
        Assert.assertTrue(isElementExsist("//label[.='Great!']"));

    }

    public boolean isElementExsist(String xpath) {
        try {
            webDriver.findElement(By.xpath(xpath));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Test
    void testTablePage() {
        //Delete elements
        webDriver.findElement(By.id("table")).click();
        webDriver.findElement(By.xpath("//tr[2]/td/input[@type = 'checkbox']")).click();
        webDriver.findElement(By.xpath("//tr[5]/td/input[@type = 'checkbox']")).click();
        webDriver.findElement(By.xpath("//input[@type = 'button' and @value = 'Delete']")).click();

        //Paste element
        webDriver.findElement(By.xpath("//div[1]/input[@type = 'text']")).sendKeys("Coca-Cola");
        webDriver.findElement(By.xpath("//div[2]/input[@type = 'text']")).sendKeys("Petya Petyvich");
        webDriver.findElement(By.xpath("//div[3]/input[@type = 'text']")).sendKeys("LA");
        webDriver.findElement(By.xpath("//input[@type = 'button' and @value = 'Add']")).click();

        //Exit
        WebElement goBackFromTable = webDriver.findElement(By.xpath("//a[contains(text(),'Great')]"));
        Assert.assertEquals(goBackFromTable.getText(), "Great! Return to menu");
        goBackFromTable.click();
        Assert.assertEquals(webDriver.manage().getCookieNamed("table").getName(), "table");
    }


    @AfterClass
    void closeDriver() {
        webDriver.quit();
    }
}
