package ua.service.impl;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
 
@Service
public class MailSender {
 
    private final static String USERNAME = "y.ivanytsky@gmail.com";
    private final static String PASSWORD = "anarhia1715-0";
    @Async
    public void sendMail(String content, String mail, String mailBody) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
            		mail));
            message.setSubject(content, "UTF-8");
            message.setText(mailBody);
            synchronized (this) {
                Transport.send(message);
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("You have some problems with connection!");
        }
    }
}
