package IPS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {

	public static WebElement element = null;

	public static WebElement usernameTextBox(WebDriver driver) {
		element = driver.findElement(By.id("associate_associate_id"));
		return element;
	}

	public static WebElement passwordTextBox(WebDriver driver) {
		element = driver.findElement(By.id("associate_password"));
		return element;
	}

	public static WebElement loginButton(WebDriver driver) {
		element = driver.findElement(By.id("submit"));
		return element;
	}

}
