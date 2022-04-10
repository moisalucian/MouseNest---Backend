package com.example.MouseNest.service;

import com.example.MouseNest.config.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private final MailProperties mailProperties;



    @Autowired
    public MailServiceImpl(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public void sendEmail(String from, String to, String subject, String content) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", mailProperties.isAuth());
        properties.put("mail.smtp.starttls.enable", mailProperties.isStarttls());
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        properties.put("mail.smtp.ssl.trust", mailProperties.getTrust());

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
