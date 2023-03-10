package com.loyalty.pkg.mailMessenger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.loyalty.pkg.config.MailConfig;

public class MailMessenger {

	public static void htmlEmailMessenger(String from, String toMail, String subject, String body)
			throws MessagingException {
		// Get Mail Config:
		JavaMailSender sender = MailConfig.getMailConfig();
		// Set Mime Message:
		MimeMessage message = sender.createMimeMessage();
		// Set Mime Message Helper:
		MimeMessageHelper htmlMessage = new MimeMessageHelper(message, true);

		// Set Mail Attributes / Properties:
		htmlMessage.setTo(toMail);
		htmlMessage.setFrom(from);
		htmlMessage.setSubject(subject);
		htmlMessage.setText(body, true);
		// Send Message:
		sender.send(message);

	}

}
