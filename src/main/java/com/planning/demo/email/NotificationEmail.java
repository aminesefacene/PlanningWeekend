package com.planning.demo.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;




@Service
public class NotificationEmail {

	@Autowired
	private JavaMailSender javaMailSender;

	public NotificationEmail(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	
	public void sendNotifications(String email, String message) throws MailException {
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("applicationweekend@gmail.com");
		mail.setSubject("Your activities for this week end");
		mail.setText(message);
		
		javaMailSender.send(mail);
		
	}
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	

	
	
	
}
