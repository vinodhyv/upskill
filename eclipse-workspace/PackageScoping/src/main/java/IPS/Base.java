package IPS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static String baseUrl;

    public  static void Intializedriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "http://ipupgradecenterauto.northamerica.cerner.net:8001/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }
}
