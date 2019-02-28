package IPS;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PackageInputPageTest {

    private static String username;
    private static String password;
    ExtentReports report;
    ExtentTest test;



    SoftAssert sa = new SoftAssert();

    @BeforeClass
    public void setUp(){
        Base.Intializedriver();
        report = ExtentReport.getInstance();
        test = report.startTest("Package input page test");
        HashMap hmap = IPS.LoadpropertiesFile.propFileLocation();
        username = String.valueOf(hmap.get("username"));
        password = String.valueOf(hmap.get("password"));

        loginPage.usernameTextBox(Base.driver).sendKeys(username);
        loginPage.passwordTextBox(Base.driver).sendKeys(password);
        loginPage.loginButton(Base.driver).click();
        homePage.scopingIcon(Base.driver).click();

    }

    @Test(dataProvider = "packagenumbers", dataProviderClass = PackageNumbersInput.class, priority = 0)
    public void inputPackageNumbers(String number) throws InterruptedException{
        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys(number);
        Thread.sleep(2000);
        PackageInputPage.submitButton(Base.driver).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(Base.driver, 100);
                wait.until
                      (ExpectedConditions.and
                        (ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("packageTable")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("teamNamesList")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("backendChangesList")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("solutionsList"))
                        )
                      );
        Thread.sleep(2000);
        PackageInputPage.packagenumberTextbox(Base.driver).clear();
        Thread.sleep(2000);
        String[] packagenumbers = {};
        packagenumbers = number.split(",");
        for (String val: packagenumbers
             ) {
            Thread.sleep(2000);
            sa.assertEquals(Base.driver.findElement(By.id("packageTable")).
                    getText().contains(val), true);
            sa.assertAll();
            test.log(LogStatus.PASS, "Validated packages");
            Thread.sleep(2000);
            PackageInputPage.packagenumberTextbox(Base.driver).clear();
            Thread.sleep(2000);
        }
    }

    @Test(priority = 1)
    public void withoutPackagenumbers() throws InterruptedException{
        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys("");
        PackageInputPage.submitButton(Base.driver).click();
        Thread.sleep(2000);
        String validationmessage = getHTML5ValidationMessage(Base.driver.findElement(By.id("packageNumber")));
        Assert.assertEquals(validationmessage, "Please fill out this field.");
        test.log(LogStatus.PASS, "No package numbers");
    }

    @Test(priority = 2)
    public void alphanumericinput() throws InterruptedException{
        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys("abcd12");
        PackageInputPage.submitButton(Base.driver).click();
        Thread.sleep(2000);
        String message = getHTML5ValidationMessage(Base.driver.findElement(By.id("packageNumber")));
        Assert.assertEquals(message,"Please match the requested format.");
        test.log(LogStatus.PASS, "Alphanumeric package numbers test");
        PackageInputPage.packagenumberTextbox(Base.driver).clear();

    }

    @Test(priority = 3)
    public void logout() {
        homePage.logout(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Thank you for using the tool !"), true);
        test.log(LogStatus.PASS, "Logged out of application");
    }

    public String getHTML5ValidationMessage(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Base.driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
    }


    @AfterTest(alwaysRun = true)
    public void tearDown(){
        Base.driver.quit();
        report.endTest(test);
        report.flush();
    }


}