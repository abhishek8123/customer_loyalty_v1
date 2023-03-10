package com.loyalty.pkg.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {
	
	@Bean
	public static JavaMailSenderImpl getMailConfig() {
		
		
		JavaMailSenderImpl emailConfig = new JavaMailSenderImpl();
		
		// set Properties
	    Properties props = emailConfig.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        // Set Mail Credentials:    ltmoghxkiqcjaedh
        emailConfig.setHost("smtp.gmail.com");
        emailConfig.setPort(587);
        emailConfig.setUsername("abhi19patil91@gmail.com");
        emailConfig.setPassword("ltmoghxkiqcjaedh");
		
		
		return emailConfig;
		
	}

}
