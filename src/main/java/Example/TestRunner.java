package Example;

import Framework.BaseTest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import org.testng.TestNG;

public class TestRunner {
  static TestNG testNg;
  
  public static String path;
  
  public static String screenShotPath = "../../selenium/SSD_Update_Screen_Shot_";
  
  public static FileWriter logger = null;
  
  public static String destinationFilePath = "";
  
  public static String loggerFilePath = "";
  
  public static boolean isLoginSuccess = false;
  
  public static String testResult = "success";
  
  public static void main(String[] args) throws ClassNotFoundException, IOException {
    testNg = new TestNG();
    path = args[0];
    System.out.println("Path = " + path);
    testNg.setTestClasses(new Class[] { SSD_Update.class });
    try {
      Date d = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      loggerFilePath = "../../selenium/SSD_Update_Log_" + dateFormat.format(d) + ".txt";
      logger = new FileWriter(loggerFilePath);
      File f = new File(loggerFilePath);
      System.out.println("filepath : " + f.getPath());
      logger.write("\n TestRunner started ... ");
      logger.write(path);
      testNg.run();
    } catch (Exception e) {
      logger.write("\n Exception occured");
      BaseTest.Sendmailstatus(e);
    } finally {
      try {
        BaseTest.browser.close();
        logger.write("\n Browser is closed in Test Runner... ");
      } catch (Exception exception) {}
      try {
        logger
          .write("\n Date = " + LocalDate.now() + "\n Time = " + LocalTime.now());
        logger.write("\n End of TestRunner");
        logger.write("\n Logger File is being closed");
        logger.flush();
        logger.close();
      } catch (IOException e) {
        System.out.println("\n Error occured while closing file");
      } 
    } 
  }
}
