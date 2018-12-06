package com.example.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUser {
	String username;
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	public static void main(String[] args) {
//		PasswordEncoder encoder=new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("hoang"));
//	}
}
