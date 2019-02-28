package IPS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import IPS.LoadpropertiesFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.*;

public class loginPageTest {

	private static WebDriver driver;
	private static String username;
	private static String password;
	private static String incorrectpassword;
	private static String incorrectusername;

	//test

	@BeforeTest
	public static void launch() {
		Base.Intializedriver();

        HashMap hmap = IPS.LoadpropertiesFile.propFileLocation();
        username = String.valueOf(hmap.get("username"));
        password = String.valueOf(hmap.get("password"));
        incorrectusername = String.valueOf(hmap.get("incorrectusername"));
        incorrectpassword = String.valueOf(hmap.get("incorrectpassword"));

	}

	@Test
	public void incorrectPasswordtest() throws InterruptedException {
		loginPage.usernameTextBox(Base.driver).sendKeys(loginPageTest.username);
		loginPage.passwordTextBox(Base.driver).sendKeys(loginPageTest.incorrectpassword);
		loginPage.loginButton(Base.driver).click();
		Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).getText()
				.contains("LDAP Authentication Failure, Check your credentials and try again"), true);
		Thread.sleep(3000);
	}

	@Test
	public void incorrectUsernametest() throws InterruptedException {
		loginPage.usernameTextBox(Base.driver).sendKeys(loginPageTest.incorrectusername);
		loginPage.passwordTextBox(Base.driver).sendKeys(loginPageTest.password);
		loginPage.loginButton(Base.driver).click();
		Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).getText()
				.contains("LDAP Authentication Failure, Check your credentials and try again"), true);
		Thread.sleep(3000);
	}

	@Test
	public void MissingPassword() throws InterruptedException {
		loginPage.usernameTextBox(Base.driver).sendKeys(loginPageTest.username);
		loginPage.loginButton(Base.driver).click();
		Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).getText()
				.contains("We cannot have blank in associate or password field"), true);
		Thread.sleep(3000);
	}

	@Test
	public void MissingUsername() throws InterruptedException {
		loginPage.passwordTextBox(Base.driver).sendKeys(loginPageTest.incorrectpassword);
		loginPage.loginButton(Base.driver).click();
		Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).getText()
				.contains("We cannot have blank in associate or password field"), true);
		Thread.sleep(3000);
	}
	

	@Test
	public static void loginTest() throws InterruptedException {

		loginPage.usernameTextBox(Base.driver).sendKeys(username);
		loginPage.passwordTextBox(Base.driver).sendKeys(password);
		loginPage.loginButton(Base.driver).click();
		Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Logged in successfully"), true);
		String URL = Base.driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://ipupgradecenterauto.northamerica.cerner.net:8001/");
        Thread.sleep(3000);
	}

    @AfterTest
    public void logout() {
        homePage.logout(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Thank you for using the tool !"), true);
    }

	@AfterTest
	public void tearDown() throws Exception {
		Base.driver.quit();
	}
}
