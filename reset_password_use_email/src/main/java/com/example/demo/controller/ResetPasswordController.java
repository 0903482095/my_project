package com.example.demo.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.PasswordResetTokenRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.User;

@Controller
public class ResetPasswordController {
	
	@Autowired
	PasswordResetTokenRepository tokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/resetpassword")
	public String resetPassword(@RequestParam String token,ModelMap modelMap) {
		PasswordResetToken resetToken=tokenRepository.findByToken(token);
		if(resetToken==null) {
			return "redirect:/forgotpassword";
		}
//		else if(resetToken.isExpired()) {
//			return null;
//		}
		User user=resetToken.getUser();
		modelMap.addAttribute("user", user);
		return "resetpassword";
	}
	
	@Transactional
	@PostMapping("/resetpassword")
	public String handleResetPassword(@RequestParam String password,@RequestParam Long id) {
		userRepository.updatePassword(password, id);
		return "redirect:/";
	}
}
