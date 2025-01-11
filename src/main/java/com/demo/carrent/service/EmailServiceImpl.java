package com.demo.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message=new SimpleMailMessage();

        //set receiver email
        message.setTo(to);

        //email email subject
        message.setSubject(subject);

        //set email body
        message.setText(text);

        //send to the receiver
        mailSender.send(message);
    }
}
