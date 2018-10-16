/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.services;

import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author bgunduz
 */
@Component
public class EmailSendService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendActivationMail(String emailAddress, String subject, String text) throws MessagingException {
        SimpleMailMessage mailMsg= new SimpleMailMessage();
        mailMsg.setSubject(subject);
        mailMsg.setText(text);
        MimeMessage mimeMsg= mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMsgHelper = new MimeMessageHelper(mimeMsg, true);
            mimeMsgHelper.setText(String.format(mailMsg.getText()), true);
            mimeMsgHelper.setTo(emailAddress);
            mimeMsg.setSubject(mailMsg.getSubject());

        } catch (MessagingException messagingException) {
            throw messagingException;
        }
        mailSender.send(mimeMsg);
    }
}

