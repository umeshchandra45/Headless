package Framework;

import Example.TestRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
@SuppressWarnings("unused")
public class BaseTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static int timeout = 300;
	
		
		public static WebDriver browser;
		  public static String TO_ADDRESS = "umeshchandra.gouni@forsysinc.com,oraclesupport@harmonicinc.com,pranita.kulkarni@forsysinc.com,shwetima.nirala@forsysinc.com";
		  public static String FROM_ADDRESS = "praveen.kothapalli@forsysinc.com";

		@BeforeMethod()
		public void Login_Page() throws Exception {
			login();
		}

		  
		  public static void waitUntilElementClickable(String locatorName, final WebElement elementToWaitFor, WebDriver browser, int timeout) {
			    wait = new WebDriverWait(browser, timeout);
			    wait.until(new Function<WebDriver, Boolean>() {
			          int j;
			          
			          public Boolean apply(WebDriver browser) {
			            this.j++;
			            if (elementToWaitFor.isEnabled())
			              try {
			                elementToWaitFor.click();
			              } catch (Exception e) {
			                return Boolean.valueOf(false);
			              }  
			            return Boolean.valueOf(true);
			          }
			        });
			  }
			  
		  public static void WaituntilElementwritable(String locatorName, final WebElement elementToWaitFor,
					WebDriver browser, String value) {

				wait = new WebDriverWait(browser, timeout);
				wait.until(new Function<WebDriver, Boolean>() {
					int j;

					public Boolean apply(WebDriver browser) {
						j++;
						if (elementToWaitFor.isEnabled()) {
							try {
								elementToWaitFor.sendKeys(value);

							} catch (Exception e) {
								return false;

							}

						}
						return true;

					}
				});

			}
			  
			  public static void WaituntilElementIsDisplayed(String locatorName, final By elementToWaitFor, WebDriver browser) {
			    wait = new WebDriverWait(browser, timeout);
			    wait.until(new Function<WebDriver, Boolean>() {
			          int j;
			          
			          public Boolean apply(WebDriver browser) {
			            this.j++;
			            try {
			              Boolean flag = Boolean.valueOf(browser.findElement(elementToWaitFor).isDisplayed());
			              if (flag.booleanValue()) {
			                browser.findElement(elementToWaitFor).click();
			              } else {
			                return Boolean.valueOf(false);
			              } 
			            } catch (Exception e) {
			              return Boolean.valueOf(false);
			            } 
			            return Boolean.valueOf(true);
			          }
			        });
			  }

		@AfterMethod()
		public void Quit_Browser() throws IOException {
			TestRunner.logger.write("\n browser is closed");
			browser.quit();
		}

		public static void Sendmailstatus(Exception e) {

			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("noreply@forsysinc.com", "nsuzjyszewrkashw");
				}
			});
			try {
			      MimeMessage mimeMessage = new MimeMessage(session);
			      mimeMessage.setFrom((Address)new InternetAddress(FROM_ADDRESS));
			      mimeMessage.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(TO_ADDRESS));
			      mimeMessage.setSubject("SSD script failed");
			      mimeMessage.setText("your test has failed for script name:Name of your scipt SSD_Update \n " + 
			          ExceptionUtils.getStackTrace(e));
			      MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
			      mimeBodyPart1
			        .setText("your test has failed for script SSD_Update \n " + ExceptionUtils.getStackTrace(e));
			      MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			      FileDataSource fileDataSource1 = new FileDataSource(TestRunner.destinationFilePath);
			      messageBodyPart2.setDataHandler(new DataHandler((DataSource)fileDataSource1));
			      messageBodyPart2.setFileName(TestRunner.destinationFilePath);
			      MimeBodyPart messageBodyPart3 = new MimeBodyPart();
			      FileDataSource fileDataSource2 = new FileDataSource(TestRunner.loggerFilePath);
			      messageBodyPart3.setDataHandler(new DataHandler((DataSource)fileDataSource2));
			      messageBodyPart3.setFileName(TestRunner.loggerFilePath);
			      MimeMultipart mimeMultipart = new MimeMultipart();
			      mimeMultipart.addBodyPart((BodyPart)mimeBodyPart1);
			      mimeMultipart.addBodyPart((BodyPart)messageBodyPart2);
			      mimeMultipart.addBodyPart((BodyPart)messageBodyPart3);
			      mimeMessage.setContent((Multipart)mimeMultipart);
			      Transport.send((Message)mimeMessage);
			      System.out.println("Done");
			} catch (MessagingException ex) {
				throw new RuntimeException(ex);

			}

		}

		public static void takeSnapShot() throws Exception {
		    Date d = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
		    String fileWithPath = String.valueOf(TestRunner.screenShotPath) + dateFormat.format(d) + ".png";
		    TakesScreenshot scrShot = (TakesScreenshot)browser;
		    File SrcFile = (File)scrShot.getScreenshotAs(OutputType.FILE);
		    File DestFile = new File(fileWithPath);
		    TestRunner.destinationFilePath = SrcFile.getPath();
		    System.out.println("srcFile = " + SrcFile.getName());
		    System.out.println("absolue Path = " + SrcFile.getPath());
		    FileUtils.copyFile(SrcFile, DestFile);
		  }

		public static boolean loginSuccessOrNot(WebDriver browser) {
			try {
				browser.findElement(By.xpath("//div[contains(text(),'Authentication failed.')]"));
				return true;
			} catch (Exception e) {
				TestRunner.isLoginSuccess = true;
				return false;
			}
		}

		
		public static void login() throws IOException, Exception {
		    try {
		      TestRunner.logger
		        .write("\n Date = " + LocalDate.now() + "\n Time = " + LocalTime.now());
		      TestRunner.logger.write("\n inside Login_Page()");
		      Properties p = new Properties();
		      InputStream inputStream = BaseTest.class.getClassLoader().getResourceAsStream("Example/config.properties");
		      p.load(inputStream);
		      WebDriverManager.chromedriver().setup();
		      ChromeOptions options = new ChromeOptions();
		      options.setPageLoadStrategy(PageLoadStrategy.NONE);
		      options.addArguments(new String[] { "--no-sandbox" });
		      options.addArguments(new String[] { "--disable-dev-shm-usage" });
		      options.addArguments(new String[] { "--window-size=1920,1080" });
		      options.setCapability("javascriptEnabled", true);
		      options.setCapability("takesScreenshot", true);
		      TestRunner.logger.write("\n Web Browses is running in headless mode");
		      options.setHeadless(true);
		      browser = (WebDriver)new ChromeDriver(options);
		      browser.manage().window().maximize();
		      browser.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
		      browser.manage().timeouts().pageLoadTimeout(100L, TimeUnit.SECONDS);
		      TestRunner.logger.write("\n Loading https://egmn.fa.us2.oraclecloud.com/ in browser");
		      browser.get(p.getProperty("url"));
		      JavascriptExecutor js = (JavascriptExecutor)browser;
		      js.executeScript("arguments[0].style.border='3px solid green'", new Object[] { browser.findElement(By.xpath("//label[contains(text(),'Username')]/../..//input")) });
		      browser.findElement(By.xpath("//label[contains(text(),'Username')]/../..//input")).click();
		      Thread.sleep(2000L);
		      browser.findElement(By.xpath("//label[contains(text(),'Username')]/../..//input")).sendKeys(new CharSequence[] { p.getProperty("Username") });
		      Thread.sleep(2000L);
		      js.executeScript("arguments[0].style.border='3px solid green'", new Object[] { browser.findElement(By.xpath("//label[contains(text(),'Password')]/../..//input")) });
		      browser.findElement(By.xpath("//label[contains(text(),'Password')]/../..//input")).click();
		      Thread.sleep(2000L);
		      browser.findElement(By.xpath("//label[contains(text(),'Password')]/../..//input")).sendKeys(new CharSequence[] { p.getProperty("Password") });
		      Thread.sleep(2000L);
		      browser.findElement(By.xpath("//input[@value='Sign in']")).click();
		      Thread.sleep(16000L);
		      TestRunner.logger.flush();
		      TestRunner.isLoginSuccess = true;
		    } catch (Exception e) {
		      StringWriter sw = new StringWriter();
		      PrintWriter pw = new PrintWriter(sw);
		      e.printStackTrace(pw);
		      String errorMsg = sw.toString();
		      TestRunner.logger.write("\n\n" + errorMsg);
		      TestRunner.logger.flush();
		      TestRunner.isLoginSuccess = false;
		      TestRunner.logger.write("\n login failed");
		      TestRunner.logger.flush();
		    } 
			try {
				@SuppressWarnings("deprecation")
				FluentWait<WebDriver> wait = new FluentWait<WebDriver>(browser).withTimeout(10, TimeUnit.SECONDS)
						.pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
				if (loginSuccessOrNot(browser)) {
					takeSnapShot();
					Sendmailstatus(new Exception());
				}
				TestRunner.logger.write("\n You have a new homepage");
				WebElement homepageicon = browser.findElement(By.xpath("//a[text()='You have a new home page!']"));
				waitUntilElementClickable("homepageicon", homepageicon, browser, timeout);
				TestRunner.logger.write("\n You have a new homepage is successfull");
			} catch (Exception e) {
				WebElement homepageicon = browser.findElement(By.xpath("//a[text()='You have a new home page!']"));
				waitUntilElementClickable("homepageicon", homepageicon, browser, timeout);
				TestRunner.logger.write("\n catch block code You have a new homepage is successfull");
			}
			TestRunner.logger.write("\n click on OrderManagement ");
			TestRunner.logger.flush();
			WebElement ordermanagement = browser.findElement(By.xpath("//*[text()='Order Management']"));
			waitUntilElementClickable("ordermanagement", ordermanagement, browser, timeout);
			WebElement childordermanagement = browser.findElement(By.xpath("(//a[text()='Order Management'])[2]"));
			waitUntilElementClickable("childordermanagement", childordermanagement, browser, timeout);
			TestRunner.logger.write("\n Date = " + java.time.LocalDate.now() + "\n Time = " + java.time.LocalTime.now());

		
	}
}


