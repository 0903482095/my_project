package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String Home(ModelMap map) {
		User user=userRepository.getOne((long) 1);
		map.addAttribute("user", user);
		return "home";
	}
}
