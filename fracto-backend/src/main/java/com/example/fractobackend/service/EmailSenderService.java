package com.example.fractobackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String emailBody) {
		SimpleMailMessage confirmation = new SimpleMailMessage();
		confirmation.setFrom("mr.akash016@gmail.com");
		confirmation.setTo(toEmail);
		confirmation.setText(emailBody);
		confirmation.setSubject(subject);
		
		mailSender.send(confirmation);
		
		
	}
}
