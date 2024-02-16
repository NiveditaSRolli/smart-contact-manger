
package com.example.demo.service;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendEmail(String subject, String message, String to) {
        boolean isSent = false;
        String host = "smtp.gmail.com";
        String username = "rollinivedita@gmail.com"; // Replace with your Gmail address
        String password = "mnxh kflx uloa rcrs"; // Replace with your Gmail password or App Password

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            //mimeMessage.setText(message);
            mimeMessage.setContent(message,"text/html");
            

            Transport.send(mimeMessage);
            System.out.println("Message sent successfully.");
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSent;
    }
}
