package IPS;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport {

    public static ExtentReports getInstance(){
        ExtentReports report;
        String Path = "C:\\Users\\vy023609\\Desktop\\Test.html";
        report = new ExtentReports(Path, false);
        return report;
    }

}
