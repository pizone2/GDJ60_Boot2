package com.iu.base.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManager {

		@Value("${spring.mail.username}")
		private String sender;
		
		@Autowired
		private JavaMailSender emailSender;
	
		public void send(MailDto mailDto) {
			SimpleMailMessage message = new SimpleMailMessage();	
			
			message.setFrom(sender);
	        message.setTo(mailDto.getAddress());
	        message.setSubject(mailDto.getTitle());
	        message.setText(mailDto.getContent());
	        emailSender.send(message);
		}
}
