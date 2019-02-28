package IPS;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Test {

    public static HashMap propFileLocation() {
        // TODO Auto-generated method stub
        File file = new File("C:\\Users\\vy023609\\eclipse-workspace\\PackageScoping\\src\\IPS\\dataFile.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Properties proObj = new Properties();
        try {
            proObj.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap hmap = new HashMap();
        hmap.put("username", proObj.getProperty("username"));
        hmap.put("password", proObj.getProperty("password"));
        hmap.put("incorrectusername", proObj.get("incorrectusername"));
        hmap.put("incorrectpassword", proObj.get("incorrectpassword"));
        return hmap;
    }
}
