package IPS;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import IPS.Base;

import java.util.HashMap;
import org.testng.annotations.Test;


public class homePageTest {

    private static String baseUrl;
    private static String username;
    private static String password;
    ExtentReports report;
    ExtentTest test;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws InterruptedException {
        report = ExtentReport.getInstance();
        test = report.startTest("HomePage");
        Base.Intializedriver();
        Thread.sleep(2000);
        HashMap hmap = IPS.LoadpropertiesFile.propFileLocation();
        username = String.valueOf(hmap.get("username"));
        password = String.valueOf(hmap.get("password"));

        loginPage.usernameTextBox(Base.driver).sendKeys(username);
        loginPage.passwordTextBox(Base.driver).sendKeys(password);
        loginPage.loginButton(Base.driver).click();
        test.log(LogStatus.INFO, "Logged into application");
    }

    @Test
    public void packageIcon() throws InterruptedException {
        homePage.scopingIcon(Base.driver).click();
        WebElement packagenumber = Base.driver.findElement(By.xpath("//div[@class='form-group-package']/label"));
        Assert.assertEquals((packagenumber).isDisplayed(), true);
        test.log(LogStatus.PASS,"Verified package icon");
        homePage.metricassessmentproductivityIcon(Base.driver).click();
        Thread.sleep(2000);
    }

    @Test
    public void mapIcon() {
        homePage.metricassessmentproductivityIcon(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='container']/h1")).getText(),
                "Dashboard View");
        test.log(LogStatus.PASS,"Verified map icon");
    }

    @Test
    public void singlerequestIcon() {
        homePage.singlerequestIcon(Base.driver).click();
        WebElement singlerequestbutton = Base.driver.findElement(By.xpath("//a[@href='/single_requests/new']"));
        Assert.assertEquals((singlerequestbutton).isDisplayed(), true);
        test.log(LogStatus.PASS,"Verified single request icon");
        homePage.metricassessmentproductivityIcon(Base.driver).click();
    }

    @AfterClass
    public void logout() {
        homePage.logout(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Thank you for using the tool !"), true);
        test.log(LogStatus.PASS,"Logged out of application");
        test.log(LogStatus.INFO, "Completed testing Home page");
        Base.driver.quit();
        report.endTest(test);
        report.flush();
    }
}
