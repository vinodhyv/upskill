package IPS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class homePage {

    public static WebElement element = null;

    public static WebElement scopingIcon(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@href='/intelligent_scopes']"));
        return element;
    }

    public static WebElement logout(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[contains(@href,'logout')]"));
        return element;
    }

    public static WebElement metricassessmentproductivityIcon(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@href='/']"));
        return element;

    }

    public static WebElement singlerequestIcon(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@href='/single_requests']"));
        return element;

    }
}
