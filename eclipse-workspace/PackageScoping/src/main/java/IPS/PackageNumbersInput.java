package IPS;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PackageNumbersInput {

    @DataProvider(name="packagenumbers")

    public Object[][] getData(){
        return new Object[][]{
              {"12321"},
              {"98671"},
              {"98672,98673"}
        };
    }
}
