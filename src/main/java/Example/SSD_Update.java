package Example;

import Framework.BaseTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SSD_Update extends BaseTest {
  public static int ORDER_NUMBER;
  
  public static String LINE_NUM;
  
  public static String ITEM_NUMBER;
  
  public static String New_Schedule_Ship_Date;
  
  public static int rowcount;
  
  @SuppressWarnings("resource")
@Test
  public void Excel_data() throws Exception {
    if (!TestRunner.isLoginSuccess)
      return; 
    try {
      int n = 1;
      for (int i = 1; i <= n; i++) {
        TestRunner.logger.write("\n Inside Excel_data() for loop");
        File f = new File(TestRunner.path);
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        sheet.getRow(0).createCell(8).setCellValue("Results");
        sheet.getRow(0).createCell(9).setCellValue("Comments");
        int rowcount = sheet.getPhysicalNumberOfRows();
        TestRunner.logger.write("\n Total number of Rows " + rowcount);
        TestRunner.logger
          .write("\n Date = " + LocalDate.now() + "\n Time = " + LocalTime.now());
        TestRunner.logger.write("\n Excel Record Number = " + i);
        if (n <= rowcount) {
          n++;
          if (sheet.getRow(i) == null) {
            TestRunner.logger.write("\n value coming as null at row = " + i);
            return;
          } 
          if (sheet.getRow(i).getCell(0) == null || sheet.getRow(i).getCell(0).getNumericCellValue() == 0.0D) {
            TestRunner.logger.write("\n value coming as 0 at row = " + i);
            return;
          } 
          ORDER_NUMBER = (int)sheet.getRow(i).getCell(0).getNumericCellValue();
          LINE_NUM = sheet.getRow(i).getCell(1).getStringCellValue().trim();
          ITEM_NUMBER = sheet.getRow(i).getCell(2).getStringCellValue().trim();
          New_Schedule_Ship_Date = sheet.getRow(i).getCell(5).getStringCellValue().trim();
          TestRunner.logger.write("\n Processing order number  = " + ORDER_NUMBER);
          TestRunner.logger.write("\n Task page is displayed");
          By overview = By.xpath("//h1[text()='Overview']");
          WaituntilElementIsDisplayed("overview", overview, browser);
          TestRunner.logger.write("\n Clicking on Task");
          Thread.sleep(12000L);
          WebElement taskicon = browser.findElement(By.xpath("//a[text()='Tasks']"));
          waitUntilElementClickable("taskicon", taskicon, browser, timeout);
          TestRunner.logger.write("\n click on Manage Fulfillment Lines");
          WebElement managefulfillmentlines = browser.findElement(By.xpath("//td[contains(text(),'Manage Fulfillment Lines')]"));
          waitUntilElementClickable("managefulfillmentlines", managefulfillmentlines, browser, timeout);
          TestRunner.logger.write("\n Click on order");
          WebElement ordervalue = browser.findElement(By.xpath("//*[contains(@id,'value20::content')]"));
          waitUntilElementClickable("ordervalue", ordervalue, browser, timeout);
          WebElement ordernumer = browser.findElement(By.xpath("//*[contains(@id,'value20::content')]"));
          String order = String.valueOf(ORDER_NUMBER);
          ordernumer.sendKeys(new CharSequence[] { order.trim() });
          TestRunner.logger.write("\n Click on Linenumber");
          WebElement fulfillmentline = browser.findElement(By.xpath("//*[contains(@id,'value30::content')]"));
          waitUntilElementClickable("fulfillmentline", fulfillmentline, browser, timeout);
          WaituntilElementwritable("fulfillmentline", fulfillmentline, browser, LINE_NUM);
          TestRunner.logger.write("\n Click on Item");
          WebElement item = browser.findElement(By.xpath("//*[contains(@id,'value50::content')]"));
          waitUntilElementClickable("item", item, browser, timeout);
          WaituntilElementwritable("item", item, browser, ITEM_NUMBER);
          TestRunner.logger.write("\n Click on Search Button");
          WebElement searchbutton = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:q1::search')]"));
          waitUntilElementClickable("searchbutton", searchbutton, browser, timeout);
          try {
            TestRunner.logger.write("\n Click on Table");
            WebElement tablevalue = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:AT1:_ATp:ATt1::db')]/table/tbody/tr[1]/td[1]"));
            waitUntilElementClickable("tablevalue", tablevalue, browser, timeout);
            Thread.sleep(6000L);
            TestRunner.logger.write("\n Click on Edit button");
            WebElement edit = browser.findElement(By.xpath("//img[contains(@title,'Edit')]"));
            JavascriptExecutor js = (JavascriptExecutor)browser;
            js.executeScript("arguments[0].click()", new Object[] { edit });
            TestRunner.logger.write("\n Click on Yes or No drop-down");
            Select sc = new Select(browser.findElement(By.xpath("//*[contains(@id,'overrideScheduleDate::content')]")));
            sc.selectByVisibleText("Yes");
            Thread.sleep(10000L);
            TestRunner.logger.write("\n Click on Schedule ship Date");
            WebElement scheduledate = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:AT1:r2:1:id1::content')]"));
            waitUntilElementClickable("scheduledate", scheduledate, browser, timeout);
            WaituntilElementwritable("scheduledate", scheduledate, browser, New_Schedule_Ship_Date);
            Thread.sleep(10000L);
            TestRunner.logger.write("\n Click on Save and Close");
            WebElement saveclose = browser.findElement(By.xpath("//*[text()='ave and Close']"));
            waitUntilElementClickable("saveclose", saveclose, browser, timeout);
            try {
              TestRunner.logger.write("\n Click on Ok button");
              WebElement okbutton = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:AT1:d9::ok')]"));
              waitUntilElementClickable("okbutton", okbutton, browser, timeout);
              TestRunner.logger.write("\n Valid Message");
              TestRunner.logger.flush();
              Thread.sleep(3000L);
              sheet.getRow(i).createCell(8).setCellValue("Pass");
            } catch (Exception e) {
              TestRunner.logger.write("\n Click on ok button if wrong date time format");
              WebElement cancelbutton = browser.findElement(By.xpath("//button[@id='d1::msgDlg::cancel']"));
              waitUntilElementClickable("cancelbutton", cancelbutton, browser, timeout);
              TestRunner.logger.write("\n Click on cancel button");
              Thread.sleep(4000L);
              WebElement wrongdatecancel = browser.findElement(By.xpath("//button[text()='ancel']"));
              waitUntilElementClickable("wrongdatecancel", wrongdatecancel, browser, timeout);
              TestRunner.logger.write("\n Fail");
              TestRunner.logger.flush();
              sheet.getRow(i).createCell(8).setCellValue("Fail");
              sheet.getRow(i).createCell(9).setCellValue("Date format is wrong");
            } 
            JavascriptExecutor jsup = (JavascriptExecutor)browser;
            jsup.executeScript("window.scrollBy(0,-400)", new Object[0]);
            TestRunner.logger.write("\n Click on Done button");
            WebElement donebutton = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:cb1')]"));
            waitUntilElementClickable("donebutton", donebutton, browser, timeout);
          } catch (Exception e1) {
            try {
              TestRunner.logger.write("\n Your request failed ok button");
              WebElement errormessage = browser.findElement(By.id("d1::msgDlg::cancel"));
              waitUntilElementClickable("errormessage", errormessage, browser, timeout);
              Thread.sleep(6000L);
              TestRunner.logger.write("\n Click on Done button if Null data coming");
              WebElement donebutton = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:cb1')]"));
              waitUntilElementClickable("donebutton", donebutton, browser, timeout);
              sheet.getRow(i).createCell(8).setCellValue("Fail");
              sheet.getRow(i).createCell(9).setCellValue("Your request failed. Contact your system administrator.");
            } catch (Exception e) {
              TestRunner.logger.write("\n Click on Done button if Null data coming");
              WebElement donebutton = browser.findElement(By.xpath("//*[contains(@id,'FulSAP:cb1')]"));
              waitUntilElementClickable("donebutton", donebutton, browser, timeout);
              sheet.getRow(i).createCell(8).setCellValue("Fail");
              sheet.getRow(i).createCell(9).setCellValue("No data for given order");
            } 
          } 
          TestRunner.logger.flush();
          try {
            FileOutputStream fos = new FileOutputStream(f);
            wb.write(fos);
            wb.close();
          } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorMsg = sw.toString();
            throw new Exception(errorMsg);
          } 
        } 
        TestRunner.logger.flush();
      } 
    } catch (Exception e) {
      TestRunner.logger.write("\n sending an email");
      TestRunner.logger.flush();
      Thread.sleep(8000L);
      takeSnapShot();
      loggingMessages(e);
      Sendmailstatus(e);
    } 
  }
  
  public static void loggingMessages(Exception e) throws IOException {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String errorMsg = sw.toString();
    TestRunner.logger.write("\n\n" + errorMsg);
    TestRunner.logger.flush();
  }
}