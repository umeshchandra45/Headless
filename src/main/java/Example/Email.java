package Example;

import java.io.IOException;
import java.util.Properties;
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
import org.apache.commons.lang3.exception.ExceptionUtils;

public class Email {
  public static String toRecepients = "oraclesupport@harmonicinc.com,umeshchandra.gouni@forsysinc.com,pranita.kulkarni@forsysinc.com,shwetima.nirala@forsysinc.com";
  
  public static String from = "praveen.kothapalli@forsysinc.com";
  
  public static void sendErrorEmails(Exception e) throws IOException {
    Session session = mailConfiguration();
    try {
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom((Address)new InternetAddress(from));
      mimeMessage.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(toRecepients));
      mimeMessage.setSubject("Script failed for RD_Update");
      mimeMessage.setText("your test has failed for script RD_Update");
      MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
      mimeBodyPart1
        .setText("\n your test has failed for script RD_Update \n " + ExceptionUtils.getStackTrace(e));
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
      TestRunner.logger.write("\n mail sent successfully");
      System.out.println("Done");
    } catch (MessagingException ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  public static void sendSuccessEmail() throws IOException {
    Session session = mailConfiguration();
    try {
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom((Address)new InternetAddress(from));
      mimeMessage.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(toRecepients));
      mimeMessage.setSubject("Script success for RD_Update");
      mimeMessage.setText("your test is success for script RD_Update");
      MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
      mimeBodyPart1.setText("\n your test is success ");
      MimeBodyPart messageBodyPart2 = new MimeBodyPart();
      FileDataSource fileDataSource1 = new FileDataSource(TestRunner.path);
      messageBodyPart2.setDataHandler(new DataHandler((DataSource)fileDataSource1));
      messageBodyPart2.setFileName(TestRunner.path);
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
      TestRunner.logger.write("\n mail sent successfully");
      System.out.println("Done");
    } catch (MessagingException ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  public static Session mailConfiguration() {
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.port", "465");
    Session session = Session.getDefaultInstance(p, new Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("noreply@forsysinc.com", "nsuzjyszewrkashw");
          }
        });
    return session;
  }
}

