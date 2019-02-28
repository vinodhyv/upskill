package IPS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PackageInputPage {

    private static WebElement element = null;

    public static WebElement packagenumberTextbox(WebDriver driver){
         element = driver.findElement(By.id("packageNumber"));
        return element;
    }

    public static WebElement submitButton(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@type='submit']"));
        return element;
    }

    public static WebElement helpIcon(WebDriver driver){
        element = driver.findElement(By.id("helpIcon"));
        return element;
    }
}
