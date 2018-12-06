package com.example.demo.controller;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.PasswordResetTokenRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.User;
import com.example.demo.utils.MyConstants;

@Controller
public class ForgotPasswordController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordResetTokenRepository tokenRepository;

	@Autowired
	JavaMailSender javaMailSender;

	@GetMapping("/forgotpassword")
	public String forgot() {
		return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String forgotpassword(@RequestParam String email, HttpServletRequest request) throws MessagingException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return "forgotpassword";
		}
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(UUID.randomUUID().toString());
		passwordResetToken.setUser(user);
//		passwordResetToken.setExpiryDate(30);

		tokenRepository.save(passwordResetToken);

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/resetpassword?token=" + passwordResetToken.getToken();
		String html = "<a href="+url+">click here</a>";

		message.setContent(html, "text/html");
		helper.setTo(email);
		helper.setSubject("Test send html email");

		this.javaMailSender.send(message);

		return "redirect:/";
	}

}
