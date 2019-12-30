package com.ditraacademy.travelagency.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String Destination, String Subject, String Text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(Destination);

        msg.setSubject(Subject);
        msg.setText(Text);

        javaMailSender.send(msg);

    }
}
