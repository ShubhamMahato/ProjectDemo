package com.example.microservices.loginregisteruser.DatabaseConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class SmtpConfig {

    @Autowired
    SmtpConfiguration smtpConfiguration;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpConfiguration.getHost());
        mailSender.setPort(smtpConfiguration.getPort());

        mailSender.setUsername(smtpConfiguration.getUsername());
        mailSender.setPassword(smtpConfiguration.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", smtpConfiguration.getAuth());
        props.put("mail.smtp.starttls.enable", smtpConfiguration.getEnable());

        return mailSender;
    }
}
