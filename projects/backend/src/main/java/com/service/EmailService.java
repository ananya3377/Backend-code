package com.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void send(String to, String subject, String body){
        // stub — integrate SMTP/SendGrid/AWS SES in production
        System.out.println("[EMAIL] to=" + to + " subject=" + subject + "\n" + body);
    }
}