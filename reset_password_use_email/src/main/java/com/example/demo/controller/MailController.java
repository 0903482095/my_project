package com.example.demo.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.utils.MyConstants;

@Controller
public class MailController {
	@Autowired
	JavaMailSender javaMailSender;
	
	@ResponseBody
	@GetMapping("/sendEmail")
	public String sendEmail() throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper=new MimeMessageHelper(message,true,"utf-8");
		String html="<h3>Im testing send a HTML email</h3>" 
					+ "<a>click here</a>";
		
		message.setContent(html,"text/html");
		helper.setTo(MyConstants.FRIEND_EMAIL);
		helper.setSubject("Test send html email");
		
		this.javaMailSender.send(message);
	
		return "done";
	}
}
