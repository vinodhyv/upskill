package IPS;

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

    SoftAssert sa = new SoftAssert();

    @BeforeTest
    public void setUp(){
        Base.Intializedriver();
        HashMap hmap = IPS.LoadpropertiesFile.propFileLocation();
        username = String.valueOf(hmap.get("username"));
        password = String.valueOf(hmap.get("password"));

        loginPage.usernameTextBox(Base.driver).sendKeys(username);
        loginPage.passwordTextBox(Base.driver).sendKeys(password);
        loginPage.loginButton(Base.driver).click();
        homePageTest.packageIcon();
    }

    @Test(dataProvider = "packagenumbers", dataProviderClass = PackageNumbersInput.class, priority = 0)
    public void inputPackageNumbers(String number){

        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys(number);
        Base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PackageInputPage.submitButton(Base.driver).click();
        Base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(Base.driver, 100);
                wait.until
                      (ExpectedConditions.and
                        (ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("packageTable")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("teamNamesList")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("backendChangesList")),
                         ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("solutionsList"))
                        )
                      );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PackageInputPage.packagenumberTextbox(Base.driver).clear();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] packagenumbers = {};
        packagenumbers = number.split(",");
        for (String val: packagenumbers
             ) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sa.assertEquals(Base.driver.findElement(By.id("packageTable")).
                    getText().contains(val), true);
            sa.assertAll();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PackageInputPage.packagenumberTextbox(Base.driver).clear();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test(priority = 1)
    public void withoutPackagenumbers(){
        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys("");
        PackageInputPage.submitButton(Base.driver).click();
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        String validationmessage = getHTML5ValidationMessage(Base.driver.findElement(By.id("packageNumber")));
        Assert.assertEquals(validationmessage, "Please fill out this field.");

    }

    @Test(priority = 2)
    public void alphanumericinput(){
        PackageInputPage.packagenumberTextbox(Base.driver).sendKeys("abcd12");
        PackageInputPage.submitButton(Base.driver).click();
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        String message = getHTML5ValidationMessage(Base.driver.findElement(By.id("packageNumber")));
        Assert.assertEquals(message,"Please match the requested format.");
        PackageInputPage.packagenumberTextbox(Base.driver).clear();

    }

    @Test(priority = 3)
    public void logout() {
        homePage.logout(Base.driver).click();
        Assert.assertEquals(Base.driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText()
                .contains("Thank you for using the tool !"), true);
    }

    public String getHTML5ValidationMessage(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Base.driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
    }


    @AfterTest(alwaysRun = true)
    public void tearDown(){
        Base.driver.quit();
    }

}