package Lesson3Ex01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class SavkkSelect {
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

    @Test (enabled = false)
    void buttonTest() {
        //Button page
        webDriver.findElement(By.id("button")).click();
        webDriver.findElement(By.id("first")).click();
        webDriver.findElement(By.xpath("//input[@value='Click me too!']")).click();
        WebElement goBack = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(goBack.getText(), "Great! Return to menu");
        goBack.click();
    }

    @Test (enabled = false)
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
    }

    @Test (enabled = false)
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
    }

    @Test
    void formAndIframeTest() {
        //Form
        webDriver.findElement(By.id("form")).click();
        webDriver.findElement(By.xpath("//div[label = 'First Name:']//input")).sendKeys("Pavel");
        webDriver.findElement(By.xpath("//div[label = 'Last Name:']//input")).sendKeys("Glinyany");
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("gh7777@mail.ru");
        webDriver.findElement(By.xpath("//input[1][@type = 'radio']")).click();
        webDriver.findElement(By.xpath("//div[label = 'Address:']//input")).sendKeys("Moscow");
        webDriver.findElement(By.xpath("//input[@type = 'file']")).sendKeys((new File("src/test/resources/hk.jpg").getAbsolutePath()));
        webDriver.findElement(By.xpath("//textarea [@cols = '50']")).sendKeys("Ton Tom");
        webDriver.findElement(By.xpath("//input[@type = 'submit']")).click();
        WebElement goBackFromForm = webDriver.findElement(By.xpath("//a[contains(text(),'Great')]"));
        Assert.assertEquals(goBackFromForm.getText(), "Great! Return to menu");
        goBackFromForm.click();

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
    }

    @AfterMethod
    void closeDriver() {
        webDriver.close();
    }
}
