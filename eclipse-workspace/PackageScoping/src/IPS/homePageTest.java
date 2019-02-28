package IPS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import IPS.Base;
import IPS.LoadpropertiesFile;
import IPS.loginPageTest;
import java.util.concurrent.TimeUnit;


public class homePageTest {

    private static String baseUrl;
    private static String username;
    private static String password;


    @BeforeTest
    public void test() throws InterruptedException {
        Base.Intializedriver();
        HashMap hmap = IPS.LoadpropertiesFile.propFileLocation();
        username = String.valueOf(hmap.get("username"));
        password = String.valueOf(hmap.get("password"));

        loginPage.usernameTextBox(Base.driver).sendKeys(username);
        loginPage.passwordTextBox(Base.driver).sendKeys(password);
        loginPage.loginButton(Base.driver).click();
    }

    @Test(priority = 0)
    public static void packageIcon() {
        homePage.scopingIcon(Base.driver).click();
        WebElement packagenumber = Base.driver.findElement(By.xpath("//div[@class='form-group-package']/label"));
        Assert.assertEquals((packagenumber).isDisplayed(), true);
    }

    @Test(priority = 3)
    public void logout() {
        homePage.logout(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Thank you for using the tool !"), true);
    }

    @Test(priority = 1)
    public void mapIcon() {
        homePage.metricassessmentproductivityIcon(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='container']/h1")).getText(),
                "Dashboard View");
    }

    @Test(priority = 2)
    public void singlerequestIcon(){
        homePage.singlerequestIcon(Base.driver).click();
        WebElement singlerequestbutton = Base.driver.findElement(By.xpath("//a[@href='/single_requests/new']"));
        Assert.assertEquals((singlerequestbutton).isDisplayed(), true);
    }

    @AfterTest
    public void tearDown() throws Exception {
        Base.driver.quit();
    }
}
