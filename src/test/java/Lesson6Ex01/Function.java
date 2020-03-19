package Lesson6Ex01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private final WebDriver webDriver;

    public Function(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected boolean assertFormat(String example) {
        Pattern pattern = Pattern.compile("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{0,2}\\s\\D");
        Matcher matcher = pattern.matcher(example);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public String getTextFromElement(By element){
        return webDriver.findElement(element).getText();
    }
    public WebElement getWebElement(By element){
        return webDriver.findElement(element);
    }
    public String getOnlyAmount(By element){
        return webDriver.findElement(element).getText().substring(13);
    }
}

